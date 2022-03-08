package com.example.bitsoapp20.local

import androidx.room.*
import com.example.bitsoapp20.domain.Currency
import com.example.bitsoapp20.domain.CurrencyWithAvailableBook

@Dao
interface CurrencyDao {
    @Query("Select * From ${DBConstantTablesName.currency}")
    suspend fun getAll(): List<Currency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(currencies: List<Currency>)

    @Update
    suspend fun updateAll(currencies: List<Currency>)

    @Transaction
    @Query("SELECT * FROM ${DBConstantTablesName.currency}")
    fun getAvailableBooksWithCurrency(): List<CurrencyWithAvailableBook>
}