package com.example.basicbankingapp.transfer

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.basicbankingapp.database.AccountsDatabase

class TransferViewModelFactory(
    private val application: Application,
    private val account: AccountsDatabase
) :
    ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransferViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TransferViewModel(application, account) as T
        }
        throw IllegalArgumentException("Unable to construct viewModel")
    }
}