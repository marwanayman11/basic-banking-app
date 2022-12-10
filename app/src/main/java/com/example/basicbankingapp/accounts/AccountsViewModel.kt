package com.example.basicbankingapp.accounts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.basicbankingapp.database.AccountsDatabase
import com.example.basicbankingapp.database.getDatabase

class AccountsViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    val accounts = database.accountDao.getAccounts()
    private val _navigateToSelectedAccount = MutableLiveData<AccountsDatabase?>()
    val navigateToSelectedAccount: LiveData<AccountsDatabase?>
        get() = _navigateToSelectedAccount

    fun displayAccountDetails(account: AccountsDatabase) {
        _navigateToSelectedAccount.value = account
    }

    fun displayAccountDetailsComplete() {
        _navigateToSelectedAccount.value = null
    }
}