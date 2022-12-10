package com.example.basicbankingapp.transactions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.basicbankingapp.database.getTransactionsDatabase

class TransactionsViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getTransactionsDatabase(application)
    val transactions = database.transactionsDao.getTransactions()
}