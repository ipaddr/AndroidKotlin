package com.example.androidkotlin.day4.paging.coroutine.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.CoroutineContext

class Main {

    fun main() {
        println("Hello!")
        CoroutineScope(GlobalScope.coroutineContext).launch {
            println("Hello again!")
            printDelay()
        }
    }

    suspend fun printDelay(){
        withContext(Dispatchers.Default){
            delay(1000L)
            print("delay from producer")
            print("\n")
        }
    }
}