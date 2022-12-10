package com.example.basicbankingapp.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.basicbankingapp.database.AccountsDatabase

class DetailsViewModel(application: Application, account: AccountsDatabase) :
    AndroidViewModel(application) {
    private val _selectedAccount = MutableLiveData<AccountsDatabase>()
    val selectedAccount: LiveData<AccountsDatabase>
        get() = _selectedAccount

    init {
        _selectedAccount.value = account
    }
    private val _navigateToTransfer = MutableLiveData<AccountsDatabase?>()
    val navigateToTransfer: LiveData<AccountsDatabase?>
        get() = _navigateToTransfer

    fun navigateToTransfer(account: AccountsDatabase) {
        _navigateToTransfer.value = account
    }

    fun navigateToTransferComplete() {
        _navigateToTransfer.value = null
    }
}