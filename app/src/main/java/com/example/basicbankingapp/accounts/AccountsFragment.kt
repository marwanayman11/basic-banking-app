package com.example.basicbankingapp.accounts

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.basicbankingapp.adapters.AccountAdapter
import com.example.basicbankingapp.R
import com.example.basicbankingapp.databinding.FragmentAccountsBinding

class AccountsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentAccountsBinding.inflate(layoutInflater)
        val application = requireNotNull(this.activity).application
        val viewModelFactory = AccountsViewModelFactory(application)
        val viewModel = ViewModelProvider(this, viewModelFactory)[AccountsViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val adapter = AccountAdapter(AccountAdapter.OnClickListener {
            viewModel.displayAccountDetails(it)
        })
        viewModel.navigateToSelectedAccount.observe(viewLifecycleOwner) {
            if (null != it) {
                this.findNavController()
                    .navigate(AccountsFragmentDirections.actionAccountsFragmentToDetailsFragment(it))
                viewModel.displayAccountDetailsComplete()
            }
        }
        binding.accounts.adapter = adapter
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.menu, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    when (menuItem.itemId) {
                        R.id.transactions -> {
                            findNavController()
                                .navigate(AccountsFragmentDirections.actionAccountsFragmentToTransactionsFragment())
                        }

                    }

                    return true
                }
            }, viewLifecycleOwner, Lifecycle.State.RESUMED
        )
        return binding.root
    }

}