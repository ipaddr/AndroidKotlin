package com.example.androidkotlin.day1.practice.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.androidkotlin.MainFragment
import com.example.androidkotlin.MainViewModel
import com.example.androidkotlin.R
import com.example.androidkotlin.databinding.FragmentCekSaldoBinding
import com.example.androidkotlin.databinding.FragmentMainBinding
import java.text.NumberFormat
import java.util.*

class CekSaldoFragment: Fragment(){

    private lateinit var binding: FragmentCekSaldoBinding
    val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCekSaldoBinding.inflate(inflater, container, false).apply {
            this.viewmodel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loginInfo.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if(!TextUtils.isEmpty(it.message))
                Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
            if (!it.status) {
                findNavController().navigate(R.id.loginFragment)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.saldo = "Saldo anda sebesar ${randomSaldo()}"
    }

    private fun randomSaldo():String{
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = Currency.getInstance("IDR")
        return format.format((1000000..10000000).random()).toString()
    }
}