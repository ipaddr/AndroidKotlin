package com.example.androidkotlin.day4.paging.coroutine.flow.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidkotlin.day4.paging.coroutine.flow.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user ORDER BY name")
    fun getUsers(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}