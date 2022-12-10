package com.example.basicbankingapp.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class AccountsDatabase constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val email: String,
    val accountNumber: String,
    val currentBalance: Double
) : Parcelable