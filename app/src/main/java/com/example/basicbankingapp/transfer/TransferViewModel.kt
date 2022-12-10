package com.example.basicbankingapp.transfer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basicbankingapp.database.AccountsDatabase
import com.example.basicbankingapp.database.TransactionsDatabase
import com.example.basicbankingapp.database.getDatabase
import com.example.basicbankingapp.database.getTransactionsDatabase
import kotlinx.coroutines.launch

class TransferViewModel(application: Application, account: AccountsDatabase) :
    AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val transactionsDatabase = getTransactionsDatabase(application)
    private val _senderAccount = MutableLiveData<AccountsDatabase?>()
    val senderAccount: LiveData<AccountsDatabase?>
        get() = _senderAccount
    val accounts = database.accountDao.getAccountsForTransfer(account.id)
    private val _receiverAccount = MutableLiveData<AccountsDatabase?>()
    val receiverAccount: LiveData<AccountsDatabase?>
        get() = _receiverAccount

    init {
        _senderAccount.value = account
    }

    fun setReceiver(account: AccountsDatabase) {
        _receiverAccount.value = account
    }

    fun validate(amount: Double): Boolean {
        return amount <= _senderAccount.value!!.currentBalance && amount != 0.0
    }

    fun update(amount: Double) {
        val senderBalance = _senderAccount.value!!.currentBalance - amount
        val receiverBalance = _receiverAccount.value!!.currentBalance + amount
        val transaction = TransactionsDatabase(
            sender = _senderAccount.value!!.accountNumber,
            receiver = _receiverAccount.value!!.accountNumber,
            amount = amount
        )
        viewModelScope.launch {
            database.accountDao.update(_senderAccount.value!!.id, senderBalance)
        }
        viewModelScope.launch {
            database.accountDao.update(_receiverAccount.value!!.id, receiverBalance)

        }
        viewModelScope.launch {
            transactionsDatabase.transactionsDao.insertTransactions(transaction)
        }
    }
}