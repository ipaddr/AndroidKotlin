package com.example.androidkotlin.day3.delegation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidkotlin.databinding.FragmentDelegateAllBinding

class ArgumentWithoutDelegateFragment: Fragment() {

    private lateinit var binding: FragmentDelegateAllBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            param1 = args.getString(Args.PARAM1)
            param2 = args.getString(Args.PARAM2)
        }
    }

    companion object {
        private object Args {
            const val PARAM1 = "param1"
            const val PARAM2 = "param2"
        }
        fun newInstance(param1: String, param2: String): ArgumentWithoutDelegateFragment =
            ArgumentWithoutDelegateFragment().apply {
                arguments = Bundle().apply {
                    putString(Args.PARAM1, param1)
                    putString(Args.PARAM2, param2)
                }
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