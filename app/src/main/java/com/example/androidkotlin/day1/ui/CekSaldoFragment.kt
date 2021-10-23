package com.example.androidkotlin.day1.ui

import android.os.Bundle
import android.view.View
import com.example.androidkotlin.MainFragment
import java.text.NumberFormat
import java.util.*

class CekSaldoFragment: MainFragment(){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.text = "Saldo anda sebesar ${randomSaldo()}"
    }

    private fun randomSaldo():String{
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = Currency.getInstance("IDR")
        return format.format((1000000..10000000).random()).toString()
    }
}