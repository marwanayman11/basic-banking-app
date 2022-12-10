package com.example.basicbankingapp.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class TransactionsDatabase constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val sender: String,
    val receiver: String,
    val amount: Double
) : Parcelable