package com.example.androidkotlin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.androidkotlin.databinding.FragmentMainBinding
import com.example.androidkotlin.day3.delegation.*


open class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    val viewModel: MainViewModel by navGraphViewModels(R.id.nav_kotlin)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false).apply {
            this.viewmodel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        delegationExample()
        testListWithRecovery()
        binding.buttonMain.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToCekSaldoFragment()
            findNavController().navigate(action)
        }
    }

    private fun delegationExample(){
        val cekSaldo = CekSaldoImpl("Rp. 1.000.000,-")
        val transfer = TransferImpl("Iip Permana", "Rp. 1.000.000,-")
        val mobileBankingApps = MobileBankingApps(cekSaldo, transfer)
        mobileBankingApps.executeFunctionCekSaldo()
        mobileBankingApps.executeFunctionTransfer()
    }

    private fun testListWithRecovery(){
        val someList = ListWithTrash<String>(mutableListOf("satu", "dua", "tiga", "empat", "lima"))
        someList.remove("dua")
        Log.d("TAG", someList.toString())
        someList.add(1, someList.recover().toString())
        Log.d("TAG", someList.toString())
    }
}