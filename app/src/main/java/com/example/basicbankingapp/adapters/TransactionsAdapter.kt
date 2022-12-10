package com.example.basicbankingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.basicbankingapp.database.TransactionsDatabase
import com.example.basicbankingapp.databinding.TransactionsItemBinding

class TransactionsAdapter :
    ListAdapter<TransactionsDatabase, TransactionsAdapter.TransactionsViewHolder>(DiffCallback) {
    class TransactionsViewHolder(private var binding: TransactionsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: TransactionsDatabase) {
            binding.transaction = transaction
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionsViewHolder {

        return TransactionsViewHolder(
            TransactionsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TransactionsViewHolder, position: Int) {
        val asteroid = getItem(position)
        holder.bind(asteroid)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<TransactionsDatabase>() {
        override fun areItemsTheSame(
            oldItem: TransactionsDatabase,
            newItem: TransactionsDatabase
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: TransactionsDatabase,
            newItem: TransactionsDatabase
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }


}