package com.example.androidkotlin.day4.paging.coroutine.flow

import android.util.Log
import com.example.androidkotlin.day4.paging.coroutine.flow.model.User
import com.example.androidkotlin.day4.paging.coroutine.flow.room.UserDao
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

class UserRepository private constructor(
    private val userDao: UserDao,
    private val usersService: NetworkService,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao, usersService: NetworkService) =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userDao, usersService).also { instance = it }
            }
    }

    val users: Flow<List<User>> get() = userDao.getUsers()

    suspend fun fetchRecentUsers() {
        val users = usersService.allUsers()
        userDao.deleteAll()
        userDao.insertAll(users)
    }

}
