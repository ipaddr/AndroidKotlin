package com.example.androidkotlin.day3.delegation

import android.util.Log

class CekSaldoImpl(val amount: String): CekSaldo {
    override fun executeFunctionCekSaldo() {
        Log.d(this.javaClass.name, "Transfer amount is $amount")
    }
}