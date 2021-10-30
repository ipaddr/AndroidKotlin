package com.example.androidkotlin.day4.paging.coroutine.flow

import com.example.androidkotlin.day4.paging.coroutine.flow.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class NetworkService {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val usersService = retrofit.create(UsersService::class.java)

    suspend fun allUsers(): List<User> = withContext(Dispatchers.IO){
        val result = usersService.getUsers()
        result.shuffled()
    }
}

interface UsersService {
    @GET("/users")
    suspend fun getUsers() : List<User>
}