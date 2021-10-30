package com.example.androidkotlin.day4.paging.coroutine.flow

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.example.androidkotlin.day4.paging.coroutine.flow.room.AppDatabase

interface ViewModelFactoryProvider {
    fun provideUserViewModelFactory(context: Context): ViewModelFactory
}

val Injector: ViewModelFactoryProvider
    get() = currentInjector

private object DefaultViewModelProvider: ViewModelFactoryProvider {
    private fun getUserRepository(context: Context): UserRepository {
        return UserRepository.getInstance(
            userDao(context),
            userService()
        )
    }

    private fun userService() = NetworkService()

    private fun userDao(context: Context) =
        AppDatabase.getInstance(context.applicationContext).userDao()

    override fun provideUserViewModelFactory(context: Context): ViewModelFactory {
        val repository = getUserRepository(context)
        return ViewModelFactory(repository)
    }
}

private object Lock

@Volatile private var currentInjector: ViewModelFactoryProvider =
    DefaultViewModelProvider


@VisibleForTesting
private fun setInjectorForTesting(injector: ViewModelFactoryProvider?) {
    synchronized(Lock) {
        currentInjector = injector ?: DefaultViewModelProvider
    }
}

@VisibleForTesting
private fun resetInjector() =
    setInjectorForTesting(null)