package com.example.androidkotlin.day8.cryptography

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import com.example.androidkotlin.MainViewModel
import com.example.androidkotlin.R
import com.example.androidkotlin.databinding.FragmentAsymmetricCryptographyBinding

class AsymmetricCryptographyFragment:Fragment() {
    private lateinit var binding: FragmentAsymmetricCryptographyBinding
    val viewModel: MainViewModel by navGraphViewModels(R.id.nav_day8)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAsymmetricCryptographyBinding.inflate(inflater, container, false).apply {
            this.viewmodel = viewModel
        }
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.genKey.setOnClickListener {
            viewModel.genKeyPair()
        }
        binding.encryptPub.setOnClickListener {
            viewModel.encryptPubkey((binding.beforeEncryptText.text.toString()))
        }
        binding.decryptPriv.setOnClickListener {
            viewModel.decryptPrivKey()
        }

        viewModel.cipherOnTextView.observe(viewLifecycleOwner, Observer {
            binding.chiperText.setText(it)
        })

        viewModel.decryptResultOnTextView.observe(viewLifecycleOwner, Observer {
            binding.afterDecryptText.setText(it)
        })
    }
}