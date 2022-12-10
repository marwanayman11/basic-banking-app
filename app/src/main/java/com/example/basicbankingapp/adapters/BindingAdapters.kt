package com.example.basicbankingapp.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.basicbankingapp.database.AccountsDatabase
import com.example.basicbankingapp.database.TransactionsDatabase

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<AccountsDatabase>?) {
    val adapter = recyclerView.adapter as AccountAdapter
    adapter.submitList(data)

}

@BindingAdapter("listTransactions")
fun bindTransactionsRecyclerView(recyclerView: RecyclerView, data: List<TransactionsDatabase>?) {
    val adapter = recyclerView.adapter as TransactionsAdapter
    adapter.submitList(data)

}