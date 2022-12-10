package com.example.basicbankingapp.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.basicbankingapp.database.AccountsDatabase

class DetailsViewModelFactory(
    private val application: Application,
    private val account: AccountsDatabase
) :
    ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailsViewModel(application, account) as T
        }
        throw IllegalArgumentException("Unable to construct viewModel")
    }
}