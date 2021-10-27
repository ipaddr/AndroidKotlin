package com.example.androidkotlin.day3

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.androidkotlin.MainViewModel
import com.example.androidkotlin.R
import com.example.androidkotlin.databinding.FragmentDay3Binding
import com.example.androidkotlin.day3.coroutine.CoroutineActivity


class Day3Fragment: Fragment() {
    private lateinit var binding: FragmentDay3Binding
    val viewModel: MainViewModel by navGraphViewModels(R.id.nav_kotlin)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDay3Binding.inflate(inflater, container, false).apply {
            this.viewmodel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.withoutDelegate.setOnClickListener {
            val param1 = "Param satu without delegate"
            val param2 = "Param dua without delegate"
            val action = Day3FragmentDirections.actionDay3FragmentToArgumentWithoutDelegateFragment2(param1, param2)
            findNavController().navigate(action)
        }

        binding.withDelegate.setOnClickListener {
            val param1 = "Param satu with delegate"
            val param2 = "Param dua with delegate"
            val action = Day3FragmentDirections.actionDay3FragmentToArgumentWithDelegateFragment2(param1, param2)
            findNavController().navigate(action)
        }

        binding.coroutine.setOnClickListener {
//            startActivity(Intent(requireActivity(), CoroutineActivity::class.java))
            findNavController().navigate(R.id.coroutineFragment)
        }
    }
}