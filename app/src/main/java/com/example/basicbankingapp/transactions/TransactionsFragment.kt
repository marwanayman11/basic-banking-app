package com.example.basicbankingapp.transactions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.basicbankingapp.adapters.TransactionsAdapter
import com.example.basicbankingapp.databinding.FragmentTransactionsBinding

class TransactionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentTransactionsBinding.inflate(layoutInflater)
        val application = requireNotNull(this.activity).application
        val viewModelFactory = TransactionsViewModelFactory(application)
        val viewModel = ViewModelProvider(this,viewModelFactory)[TransactionsViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val adapter = TransactionsAdapter()
        binding.transactions.adapter = adapter
        return binding.root
    }
}