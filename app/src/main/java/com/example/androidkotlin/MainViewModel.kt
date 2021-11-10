package com.example.androidkotlin

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.text.TextUtils
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.androidkotlin.day1.practice.data.LoginInfo
import com.example.androidkotlin.day1.practice.data.User
import java.io.UTFDataFormatException
import java.lang.StringBuilder
import java.security.KeyPair
import java.security.KeyPairGenerator
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

class MainViewModel(): ViewModel() {

    var text: String = "Selamat datang, silahkan login untuk mengguanakan fitur aplikasi ini!"
    var user: User? = User(username = "user", password = "pass")


    //region login
    var loginInfo = MutableLiveData<LoginInfo>(LoginInfo(false, ""))
    fun login(){
        if (user?.username.equals("user") && user?.password.equals("pass")){
            val message = "login success, " +
                    "data cointain user ${user?.username}, passwprd as ${user?.password}"
            val result = LoginInfo(true, message)
            loginInfo.value = result

        } else {
            val message = "login fail, " +
                    "data cointain user ${user?.username}, password as ${user?.password}"
            val result = LoginInfo(false, message)
            loginInfo.value = result
        }
    }
    //endregion

    //region cek saldo
    var saldo: String? = "0.0"
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

    //region day 3
    var day3Argument: String = ""
    //endregion

    // region day 8

    // symmetric
    lateinit var ciphertext: ByteArray
    lateinit var keygen: KeyGenerator
    lateinit var key: SecretKey
    var cipherOnTextView = MutableLiveData<String>()
    var decryptResultOnTextView = MutableLiveData<String>()

    fun symmetricEncrypt(text: String?){
        if (TextUtils.isEmpty(text))
            return
        val plaintext: ByteArray = text!!.toByteArray()
        keygen = KeyGenerator.getInstance("AES")
        keygen.init(256)
        key = keygen.generateKey()
        val cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
        cipher.init(Cipher.ENCRYPT_MODE, key)
        ciphertext = cipher.doFinal(plaintext)
        cipherOnTextView.value = String(ciphertext, Charsets.UTF_8)
    }

    fun symmetricDecrypt(){
        if (ciphertext.isEmpty())
            return

        val cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
        cipher.init(Cipher.DECRYPT_MODE, key)
        val ciphertext: ByteArray = cipher.doFinal(ciphertext)
        val string = String(ciphertext)
        decryptResultOnTextView.value = string
    }



    // asymmetric
    lateinit var kp: KeyPair

    @RequiresApi(Build.VERSION_CODES.M)
    fun genKeyPair(){
        /*
         * Generate a new EC key pair entry in the Android Keystore by
         * using the KeyPairGenerator API. The private key can only be
         * used for signing or verification and only with SHA-256 or
         * SHA-512 as the message digest.
         */
        val kpg: KeyPairGenerator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA)
        kpg.initialize(1024)

        kp = kpg.generateKeyPair()
    }

    fun encryptPubkey(text: String?){
        if (TextUtils.isEmpty(text))
            return

        val plaintext: ByteArray = text!!.toByteArray()
        val cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding")
        cipher.init(Cipher.ENCRYPT_MODE, kp.public)
        ciphertext = cipher.doFinal(plaintext)
        cipherOnTextView.value = String(ciphertext, Charsets.UTF_8)
    }

    fun decryptPrivKey(){
        if (ciphertext.isEmpty())
            return

        val cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding")
        cipher.init(Cipher.DECRYPT_MODE, kp.private)
        val ciphertext: ByteArray = cipher.doFinal(ciphertext)
        val string = String(ciphertext)
        decryptResultOnTextView.value = string
    }

    fun encryptPrivkey(){

    }

    fun decryptPubKey(){

    }

    // endregion
}