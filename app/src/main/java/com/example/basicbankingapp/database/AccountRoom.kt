package com.example.basicbankingapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg accounts: AccountsDatabase)

    @Query("SELECT * FROM accountsdatabase")
    fun getAccounts(): LiveData<List<AccountsDatabase>>

    @Query("SELECT * FROM accountsdatabase WHERE id != :id ")
    fun getAccountsForTransfer(id: Long): LiveData<List<AccountsDatabase>>

    @Query("UPDATE accountsdatabase SET currentBalance = :value WHERE id = :id")
    suspend fun update(id: Long, value: Double)

}

@Database(entities = [AccountsDatabase::class], version = 1)
abstract class AccountRoom : RoomDatabase() {
    abstract val accountDao: AccountDao
}

private lateinit var INSTANCE: AccountRoom
fun getDatabase(context: Context): AccountRoom {
    synchronized(AccountRoom::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AccountRoom::class.java,
                "accounts"
            ).fallbackToDestructiveMigration().build()
        }
    }
    return INSTANCE
}