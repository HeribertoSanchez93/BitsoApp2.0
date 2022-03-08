package com.example.bitsoapp20.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bitsoapp20.domain.AvailableBook
import com.example.bitsoapp20.domain.Currency

@Database(
    entities = [
        Currency::class, AvailableBook::class
    ],
    version = 1
)
abstract class BitsoAppDataBase : RoomDatabase() {
    abstract fun getCurrencyDao(): CurrencyDao
    abstract fun getAvailableBooks(): AvailableBooksDao
}