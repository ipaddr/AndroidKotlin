package com.example.androidkotlin.day4.paging.coroutine.flow.model

import com.google.gson.annotations.SerializedName

data class Geo(

    @field:SerializedName("lng")
    val lng: String,

    @field:SerializedName("lat")
    val lat: String
)