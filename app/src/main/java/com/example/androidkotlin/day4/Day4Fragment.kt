package com.example.androidkotlin.day4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidkotlin.databinding.FragmentDay4Binding

class Day4Fragment: Fragment() {
    private lateinit var binding: FragmentDay4Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDay4Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.day4Flow.setOnClickListener {
            val action = Day4FragmentDirections.actionDay4FragmentToUserFragment()
            findNavController().navigate(action)
        }

        binding.day4Paging.setOnClickListener {
            val action = Day4FragmentDirections.actionDay4FragmentToGithubSearchRepositoryFragment()
            findNavController().navigate(action)
        }
    }
}