package com.example.androidkotlin.day3.coroutine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.navGraphViewModels
import com.example.androidkotlin.MainViewModel
import com.example.androidkotlin.R
import com.example.androidkotlin.databinding.FragmentCoroutineBinding
import com.google.android.material.snackbar.Snackbar

class CoroutineFragment : Fragment() {

    private lateinit var binding: FragmentCoroutineBinding
    private lateinit var viewModel: CoroutineViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        // Get MainViewModel by passing a database to the factory
        val repository = CoroutineRepository(getNetworkService())
        viewModel = ViewModelProviders
            .of(this, CoroutineViewModel.FACTORY(repository))
            .get(CoroutineViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoroutineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.setOnClickListener {
            viewModel.onMainViewClicked()
        }

        // update the title when the [MainViewModel.title] changes
        viewModel.title.observe(viewLifecycleOwner) { value ->
            value?.let {
                binding.// When rootLayout is clicked call onMainViewClicked in ViewModel
                rootLayout.setOnClickListener {
                    viewModel.onMainViewClicked()
                }

                // update the title when the [MainViewModel.title] changes
                viewModel.title.observe(viewLifecycleOwner) { value ->
                    value?.let {
                        binding.someText.text = it
                    }
                }

                viewModel.taps.observe(viewLifecycleOwner) { value ->
                    binding.tap.text = value
                }

                // show the spinner when [MainViewModel.spinner] is true
                viewModel.spinner.observe(viewLifecycleOwner) { value ->
                    value.let { show ->
                        binding.spinner.visibility = if (show) View.VISIBLE else View.GONE
                    }
                }

                // Show a snackbar whenever the [ViewModel.snackbar] is updated a non-null value
                viewModel.snackbar.observe(viewLifecycleOwner) { text ->
                    text?.let {
                        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show()
                        viewModel.onSnackbarShown()
                    }
                }
            }
        }

        viewModel.taps.observe(viewLifecycleOwner) { value ->
            binding.tap.text = value
        }

        // show the spinner when [MainViewModel.spinner] is true
        viewModel.spinner.observe(viewLifecycleOwner) { value ->
            value.let { show ->
                binding.spinner.visibility = if (show) View.VISIBLE else View.GONE
            }
        }

        // Show a snackbar whenever the [ViewModel.snackbar] is updated a non-null value
        viewModel.snackbar.observe(viewLifecycleOwner) { text ->
            text?.let {
                Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show()
                viewModel.onSnackbarShown()
            }
        }
    }

}