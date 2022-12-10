package com.example.basicbankingapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TransactionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactions(vararg transactions: TransactionsDatabase)

    @Query("SELECT * FROM transactionsdatabase")
    fun getTransactions(): LiveData<List<TransactionsDatabase>>

}

@Database(entities = [TransactionsDatabase::class], version = 1)
abstract class TransactionsRoom : RoomDatabase() {
    abstract val transactionsDao: TransactionsDao
}

private lateinit var INSTANCE: TransactionsRoom
fun getTransactionsDatabase(context: Context): TransactionsRoom {
    synchronized(TransactionsRoom::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                TransactionsRoom::class.java,
                "transactions"
            ).fallbackToDestructiveMigration().build()
        }
    }
    return INSTANCE
}