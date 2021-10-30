package com.example.androidkotlin.day4.paging.coroutine.flow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.androidkotlin.R
import com.example.androidkotlin.databinding.FragmentUserBinding


class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private val viewModel: UserViewModel by viewModels {
        Injector.provideUserViewModelFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.userButton.setOnClickListener {
            viewModel.buttonClick()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.users.observe(viewLifecycleOwner) { users ->
            binding.userTextView.text = users.toString()
        }
    }

}