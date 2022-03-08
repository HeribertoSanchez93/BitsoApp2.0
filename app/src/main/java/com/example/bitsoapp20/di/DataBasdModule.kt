package com.example.bitsoapp20.di

import android.content.Context
import androidx.room.Room
import com.example.bitsoapp20.local.BitsoAppDataBase
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

object DataBasdModule {
    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext applicationContext: Context) =
        Room.databaseBuilder(
            applicationContext,
            BitsoAppDataBase::class.java, "BitsoDataBase"
        )
            .fallbackToDestructiveMigration()
            .build()
    @Singleton
    @Provides
    fun provideCurrencyDao(db: BitsoAppDataBase) = db.getCurrencyDao()

    @Singleton
    @Provides
    fun provideAvailableBooksDao(db: BitsoAppDataBase) = db.getAvailableBooks()
}