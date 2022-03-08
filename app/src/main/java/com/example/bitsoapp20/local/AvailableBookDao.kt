package com.example.bitsoapp20.local

import androidx.room.*
import com.example.bitsoapp20.domain.AvailableBook

@Dao
interface AvailableBooksDao {
    @Query("Select * From ${DBConstantTablesName.availableBook}")
    suspend fun getAll(): List<AvailableBook>

    @Query("Select * From ${DBConstantTablesName.availableBook} Where book Like :book || '%'")
    suspend fun getSelectedBooks(book: String?): List<AvailableBook>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(availableBooks: List<AvailableBook>?)

    @Update
    suspend fun updateAll(availableBooks: List<AvailableBook>)
}