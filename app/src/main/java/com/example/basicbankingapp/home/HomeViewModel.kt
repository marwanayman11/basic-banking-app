package com.example.basicbankingapp.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicbankingapp.database.AccountsDatabase
import com.example.basicbankingapp.database.getDatabase
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val accounts: List<AccountsDatabase> = listOf(
        AccountsDatabase(
            name = "Marwan Ayman",
            email = "marwan@gmail.com",
            accountNumber = "xxxxxxxxx123",
            currentBalance = 2000.0
        ),
        AccountsDatabase(
            name = "Omar Mohamed",
            email = "omar@gmail.com",
            accountNumber = "xxxxxxxxx456",
            currentBalance = 2000.0
        ),
        AccountsDatabase(
            name = "Mohamed Ahmed",
            email = "mohamed@gmail.com",
            accountNumber = "xxxxxxxxx789",
            currentBalance = 2000.0
        ),
        AccountsDatabase(
            name = "Ahmed Mostafa",
            email = "ahmed@gmail.com",
            accountNumber = "xxxxxxxxx112",
            currentBalance = 2000.0
        ),
        AccountsDatabase(
            name = "Maged Samy",
            email = "maged@gmail.com",
            accountNumber = "xxxxxxxxx113",
            currentBalance = 2000.0
        ),
        AccountsDatabase(
            name = "Hoda Mohamed",
            email = "hoda@gmail.com",
            accountNumber = "xxxxxxxxx114",
            currentBalance = 2000.0
        ),
        AccountsDatabase(
            name = "Samir Mohamed",
            email = "samir@gmail.com",
            accountNumber = "xxxxxxxxx115",
            currentBalance = 2000.0
        ),
        AccountsDatabase(
            name = "Karim Tarek",
            email = "karim@gmail.com",
            accountNumber = "xxxxxxxxx116",
            currentBalance = 2000.0
        ),
        AccountsDatabase(
            name = "Mona Ahmed",
            email = "mona@gmail.com",
            accountNumber = "xxxxxxxxx117",
            currentBalance = 2000.0
        ),
        AccountsDatabase(
            name = "Salwa Tarek",
            email = "salwa@gmail.com",
            accountNumber = "xxxxxxxxx118",
            currentBalance = 2000.0
        ),
    )

    fun insert() {
        for (account in accounts) {
            viewModelScope.launch {
                database.accountDao.insertAll(account)
            }

        }
    }
}