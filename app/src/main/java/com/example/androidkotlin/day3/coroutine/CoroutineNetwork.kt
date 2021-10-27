package com.example.androidkotlin.day3.coroutine

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class CoroutineNetwork {
}

private val service: DummyNetwork by lazy {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(DummyNetworkInterceptor())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    retrofit.create(DummyNetwork::class.java)
}

fun getNetworkService() = service

/**
 * Main network interface which will fetch a new welcome title for us
 */
interface DummyNetwork {
    @GET("next_title.json")
    suspend fun fetchNextTitle(): String
}