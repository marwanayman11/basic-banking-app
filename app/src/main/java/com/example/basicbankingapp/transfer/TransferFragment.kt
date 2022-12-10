package com.example.basicbankingapp.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.basicbankingapp.databinding.FragmentTransferBinding

class TransferFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentTransferBinding.inflate(layoutInflater)
        val account = TransferFragmentArgs.fromBundle(requireArguments()).account
        val application = requireNotNull(this.activity).application
        val viewModelFactory = TransferViewModelFactory(application, account)
        val viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[TransferViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.select.setOnClickListener {
            this.findNavController().navigate(
                TransferFragmentDirections.actionTransferFragmentToSelectFragment(
                    account
                )
            )

        }
        binding.transferAmount.setOnClickListener {
            if(binding.amountEt.text.isNullOrEmpty()||binding.realReceiver.text.isNullOrEmpty()){
                Toast.makeText(requireContext(),"Must specify an amount and a receiver account",Toast.LENGTH_SHORT).show()
            }
            else {
                val amount = binding.amountEt.text.toString().toDouble()
                if(viewModel.validate(amount)){
                    viewModel.update(amount)
                    this.findNavController().navigate(TransferFragmentDirections.actionTransferFragmentToAccountsFragment())
                }
                else{
                    Toast.makeText(requireContext(),"Must specify a valid amount",Toast.LENGTH_SHORT).show()
                }

            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().viewModelStore.clear()
    }

}