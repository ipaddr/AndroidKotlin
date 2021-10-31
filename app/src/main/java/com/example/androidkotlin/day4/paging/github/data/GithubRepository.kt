/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.androidkotlin.day4.paging.github.data

import android.util.Log
import com.example.androidkotlin.day4.paging.github.api.GithubService
import com.example.androidkotlin.day4.paging.github.api.IN_QUALIFIER
import com.example.androidkotlin.day4.paging.github.model.Repo
import com.example.androidkotlin.day4.paging.github.model.RepoSearchResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import retrofit2.HttpException
import java.io.IOException

private const val GITHUB_STRTING_PAGE_INDEX = 1

class GithubRepository(private val service: GithubService) {

    private val inMemoryCache = mutableListOf<Repo>()

    private val searchResult = MutableSharedFlow<RepoSearchResult>(replay = 1)

    private var lastRequestedPage = GITHUB_STRTING_PAGE_INDEX

    private var isRequestedInProgress = false

    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }

    suspend fun getSearchResultStream(query: String): Flow<RepoSearchResult>{
        Log.d("GithubRepository", "New query: $query")
        lastRequestedPage = 1
        inMemoryCache.clear()
        requestAndSaveData(query)
        return searchResult
    }

    suspend fun requestMore(query: String){
        if (isRequestedInProgress) return
        val successful = requestAndSaveData(query)
        if (successful)
            lastRequestedPage++
    }

    suspend fun requestAndSaveData(query: String): Boolean {
        isRequestedInProgress = true
        var succesfull = false

        val apiQuery = query + IN_QUALIFIER
        try {
            val response = service.searchRepos(apiQuery, lastRequestedPage, NETWORK_PAGE_SIZE)
            val repos = response.items ?: emptyList()
            inMemoryCache.addAll(repos)
            val reposByName = reposByName(query)
            searchResult.emit(RepoSearchResult.Success(reposByName))
            succesfull = true
        } catch (exception: IOException){
            searchResult.emit(RepoSearchResult.Error(exception))
        } catch (exception: HttpException){
            searchResult.emit(RepoSearchResult.Error(exception))
        }
        isRequestedInProgress = false
        return succesfull
    }

    private fun reposByName(query: String): List<Repo> {
        return inMemoryCache.filter {
            it.name.contains(query, true) ||
                    (it.description != null && it.description.contains(query, true))
        }.sortedWith( compareByDescending<Repo> { it.stars }.thenBy { it.name })
    }

}