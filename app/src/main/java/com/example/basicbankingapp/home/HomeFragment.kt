package com.example.basicbankingapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.basicbankingapp.R
import com.example.basicbankingapp.databinding.FragmentHomeBinding
import com.example.basicbankingapp.local.SessionManger

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val home = SessionManger.getBoolean(requireActivity().applicationContext, "home")
        if (home) {
            this.findNavController().navigate(R.id.action_homeFragment_to_accountsFragment)
        }
        val binding = FragmentHomeBinding.inflate(layoutInflater)
        val application = requireNotNull(this.activity).application
        val viewModelFactory = HomeViewModelFactory(application)
        val viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[HomeViewModel::class.java]
        binding.lifecycleOwner = this
        binding.getStarted.setOnClickListener {
            viewModel.insert()
            SessionManger.saveBoolean(requireActivity().applicationContext, "home", true)
            this.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToAccountsFragment())
        }
        return binding.root
    }


}