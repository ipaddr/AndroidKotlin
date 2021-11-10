package com.example.androidkotlin.day8

import android.os.Build
import android.os.Bundle
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.androidkotlin.MainViewModel
import com.example.androidkotlin.R
import com.example.androidkotlin.databinding.FragmentDay8Binding
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.Signature

class Day8Fragment: Fragment() {

    lateinit var binding: FragmentDay8Binding
    val viewModel: MainViewModel by navGraphViewModels(R.id.nav_day8)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDay8Binding.inflate(inflater, container, false).apply {
            this.viewmodel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.symmetric.setOnClickListener {
            val action = Day8FragmentDirections.actionDay8FragmentToSymmetricCryptographyFragment()
            findNavController().navigate(action)
        }
        binding.asymmetric.setOnClickListener {
            val action = Day8FragmentDirections.actionDay8FragmentToAsymmetricCryptographyFragment()
            findNavController().navigate(action)
        }
    }

}