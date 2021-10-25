package com.example.androidkotlin.day3.delegation

import android.util.Log

class TransferImpl(val recipient: String, val amount: String):Transfer {
    override fun executeFunctionTransfer() {
        Log.d(this.javaClass.name, "Transfer success to $recipient with amount is $amount")
    }
}