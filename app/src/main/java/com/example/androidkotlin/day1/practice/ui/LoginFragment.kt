package com.example.androidkotlin.day1.practice.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.androidkotlin.MainViewModel
import com.example.androidkotlin.R
import com.example.androidkotlin.databinding.FragmentLoginBinding

class LoginFragment: Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: MainViewModel by navGraphViewModels(R.id.nav_kotlin)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false).apply {
            this.viewmodel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loginInfo.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
            if (it.status)
                findNavController().popBackStack()

            binding.username
        })
    }

}