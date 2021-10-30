package com.example.androidkotlin.day4.paging.coroutine.flow.model

import com.google.gson.annotations.SerializedName

data class Company(

    @field:SerializedName("bs")
    val bs: String,

    @field:SerializedName("catchPhrase")
    val catchPhrase: String,

    @field:SerializedName("name")
    val name: String
)