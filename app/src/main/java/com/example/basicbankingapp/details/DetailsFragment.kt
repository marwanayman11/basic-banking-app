package com.example.basicbankingapp.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.basicbankingapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentDetailsBinding.inflate(layoutInflater)
        val application = requireNotNull(this.activity).application
        val account = DetailsFragmentArgs.fromBundle(requireArguments()).account
        val viewModelFactory = DetailsViewModelFactory(application, account)
        val viewModel = ViewModelProvider(this, viewModelFactory)[DetailsViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.navigateToTransfer.observe(viewLifecycleOwner) {
            if (null != it) {
                this.findNavController()
                    .navigate(DetailsFragmentDirections.actionDetailsFragmentToTransferFragment(it))
                viewModel.navigateToTransferComplete()
            }
        }
        binding.transfer.setOnClickListener {
            viewModel.navigateToTransfer(account)
        }
        return binding.root
    }
}