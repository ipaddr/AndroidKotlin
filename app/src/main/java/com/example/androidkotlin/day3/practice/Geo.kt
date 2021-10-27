package com.example.androidkotlin.day3.practice

import com.google.gson.annotations.SerializedName

data class Geo (

	@SerializedName("lat") val lat : Double,
	@SerializedName("lng") val lng : Double
)