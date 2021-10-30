package com.example.androidkotlin.day4.paging.coroutine.flow.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class User(

    @PrimaryKey @ColumnInfo(name = "id")
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("website")
    val website: String,

    @field:SerializedName("address")
    val address: Address,

    @field:SerializedName("phone")
    val phone: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("company")
    val company: Company,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("username")
    val username: String
)