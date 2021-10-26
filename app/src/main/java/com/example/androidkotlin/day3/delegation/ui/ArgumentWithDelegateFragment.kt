package com.example.androidkotlin.day3.delegation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidkotlin.databinding.FragmentDelegateAllBinding

class ArgumentWithDelegateFragment: Fragment() {

    private lateinit var binding: FragmentDelegateAllBinding
    private var param1: String? by FragmentArgumentDelegate<String>()
    private var param2: String? by FragmentArgumentDelegate<String>()

    companion object {
        fun newInstance(param1: String, param2: String): ArgumentWithDelegateFragment =
            ArgumentWithDelegateFragment().apply {
                this.param1 = param1
                this.param2 = param2
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDelegateAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textDelegate.setText("text 1 $param1 and text 2 $param2")
    }
}