package com.example.androidkotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidkotlin.day1.practice.data.User

class MainViewModel: ViewModel() {
    var text: String = "Selamat datang, silahkan login untuk mengguanakan fitur aplikasi ini!"
    var user: User? = User(username = "user", password = "123456")

    //region login
    var loginMessage = MutableLiveData<String>()
    fun login(){
        // login will add data to sharedpref
        loginMessage.postValue("login function execute, " +
                "data cointain user ${user?.username}, passwprd as ${user?.password}")
    }
    //endregion

    // region fragment transfer
    var transferAmount: String? = "0"
    var cancelMessage = MutableLiveData<String>()
    var nextMessage = MutableLiveData<String>()
    fun cancel(){
        cancelMessage.postValue("cancel function execute with param ${transferAmount}")
    }

    fun next(){
        nextMessage.postValue("cancel function execute with param ${transferAmount}")
    }
    // endregion
}