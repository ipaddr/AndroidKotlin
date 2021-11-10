package com.example.androidkotlin.day8.cryptography

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import com.example.androidkotlin.MainViewModel
import com.example.androidkotlin.R
import com.example.androidkotlin.databinding.FragmentSymmetricCryptographyBinding

class SymmetricCryptographyFragment: Fragment() {

    private lateinit var binding: FragmentSymmetricCryptographyBinding
    val viewModel: MainViewModel by navGraphViewModels(R.id.nav_day8)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSymmetricCryptographyBinding.inflate(inflater, container, false).apply {
            this.viewmodel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.encrypt.setOnClickListener {
            viewModel.symmetricEncrypt(binding.beforeEncryptText.text.toString())
        }
        binding.decrypt.setOnClickListener {
            viewModel.symmetricDecrypt()
        }

        viewModel.cipherOnTextView.observe(viewLifecycleOwner, Observer {
            binding.chiperText.setText(it)
        })

        viewModel.decryptResultOnTextView.observe(viewLifecycleOwner, Observer {
            binding.afterDecryptText.setText(it)
        })
    }

}