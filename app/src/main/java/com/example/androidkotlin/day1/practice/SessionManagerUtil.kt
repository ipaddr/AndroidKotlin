package com.example.androidkotlin.day1.practice

import android.content.Context
import java.util.*

class SessionManagerUtil {
    fun startUserSession(context: Context, expiredIn: Int) {
        val calendar = Calendar.getInstance()
        val userLoggedTime = calendar.time
        calendar.time = userLoggedTime
        calendar.add(Calendar.SECOND, expiredIn)
        val expiryTime = calendar.time
        val sharedPreferences =
            context.getSharedPreferences(SESSION_PREFERENCE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putLong(SESSION_EXPIRY_TIME, expiryTime.time)
        editor.apply()
    }

    fun isSessionActive(context: Context, currentTime: Date): Boolean {
        val sessionExpiresAt = Date(getExpiryDateFromPreference(context))
        return !currentTime.after(sessionExpiresAt)
    }

    private fun getExpiryDateFromPreference(context: Context): Long {
        return context.getSharedPreferences(SESSION_PREFERENCE, Context.MODE_PRIVATE)
            .getLong(SESSION_EXPIRY_TIME, 0)
    }

    fun storeUserToken(context: Context, token: String?) {
        val editor = context.getSharedPreferences(SESSION_PREFERENCE, Context.MODE_PRIVATE).edit()
        editor.putString(SESSION_TOKEN, token)
        editor.apply()
    }

    fun getUserToken(context: Context): String? {
        return context.getSharedPreferences(SESSION_PREFERENCE, Context.MODE_PRIVATE)
            .getString(SESSION_TOKEN, "")
    }

    fun endUserSession(context: Context) {
        clearStoredData(context)
    }

    private fun clearStoredData(context: Context) {
        val editor = context.getSharedPreferences(SESSION_PREFERENCE, Context.MODE_PRIVATE).edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        const val SESSION_PREFERENCE =
            "com.example.basicandroid.SessionManagerUtil.SESSION_PREFERENCE"
        const val SESSION_TOKEN = "com.example.basicandroid.SessionManagerUtil.SESSION_TOKEN"
        const val SESSION_EXPIRY_TIME =
            "com.example.basicandroid.SessionManagerUtil.SESSION_EXPIRY_TIME"
        private var INSTANCE: SessionManagerUtil? = null
        val instance: SessionManagerUtil?
            get() {
                if (INSTANCE == null) {
                    INSTANCE = SessionManagerUtil()
                }
                return INSTANCE
            }
    }
}