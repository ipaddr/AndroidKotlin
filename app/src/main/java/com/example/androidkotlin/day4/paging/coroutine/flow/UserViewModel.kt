package com.example.androidkotlin.day4.paging.coroutine.flow

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository): ViewModel() {

    val users = repository.users.asLiveData()

    fun buttonClick(){
        viewModelScope.launch {
            repository.fetchRecentUsers()
        }
    }

}