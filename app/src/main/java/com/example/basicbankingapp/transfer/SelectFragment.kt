package com.example.basicbankingapp.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.basicbankingapp.adapters.AccountAdapter
import com.example.basicbankingapp.databinding.FragmentSelectBinding

class SelectFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentSelectBinding.inflate(layoutInflater)
        val account = SelectFragmentArgs.fromBundle(requireArguments()).account
        val application = requireNotNull(this.activity).application
        val viewModelFactory = TransferViewModelFactory(application, account)
        val viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[TransferViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val adapter = AccountAdapter(AccountAdapter.OnClickListener {
            viewModel.setReceiver(it)
            this.findNavController().popBackStack()
        })
        binding.accounts.adapter = adapter
        return binding.root
    }


}