package com.example.androidkotlin.day4.paging.coroutine.flow.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.androidkotlin.day4.paging.coroutine.flow.model.*

@Database(entities = [User::class], version = 1, exportSchema = false)
@TypeConverters(RoomConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(
                        context
                    ).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addTypeConverter(RoomConverter())
                .build()
        }
    }
}

private const val DATABASE_NAME = "user-flow-db"