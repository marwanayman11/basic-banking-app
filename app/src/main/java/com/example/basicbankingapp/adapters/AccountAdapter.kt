package com.example.basicbankingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.basicbankingapp.database.AccountsDatabase
import com.example.basicbankingapp.databinding.AccountItemBinding

class AccountAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<AccountsDatabase, AccountAdapter.AccountViewHolder>(DiffCallback) {
    class AccountViewHolder(private var binding: AccountItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(account: AccountsDatabase) {
            binding.account = account
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {

        return AccountViewHolder(
            AccountItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val asteroid = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(asteroid)
        }
        holder.bind(asteroid)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<AccountsDatabase>() {
        override fun areItemsTheSame(
            oldItem: AccountsDatabase,
            newItem: AccountsDatabase
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: AccountsDatabase,
            newItem: AccountsDatabase
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(val clickListener: (AccountsDatabase) -> Unit) {
        fun onClick(account: AccountsDatabase) = clickListener(account)
    }

}