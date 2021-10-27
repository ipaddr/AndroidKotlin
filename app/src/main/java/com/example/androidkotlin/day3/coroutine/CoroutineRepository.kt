package com.example.androidkotlin.day3.coroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class CoroutineRepository(val network: DummyNetwork) {

    val title: MutableLiveData<String?> = MutableLiveData("Please tap to change the title")

    suspend fun refreshTitle() {
        try {
            val result = withTimeout(5_000) {
                network.fetchNextTitle()
            }
            title.postValue(result)
        } catch (error: Throwable) {
            throw TitleRefreshError("Unable to refresh title", error)
        }
    }

    fun refreshTitleInterop(titleRefreshCallback: TitleRefreshCallback) {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            try {
                refreshTitle()
                titleRefreshCallback.onCompleted()
            } catch (throwable: Throwable) {
                titleRefreshCallback.onError(throwable)
            }
        }
    }
}

class TitleRefreshError(message: String, cause: Throwable) : Throwable(message, cause)

interface TitleRefreshCallback {
    fun onCompleted()
    fun onError(cause: Throwable)
}