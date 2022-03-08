package com.example.bitsoapp20.domain

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["code"], unique = true)])
data class Currency (

    @PrimaryKey(autoGenerate = true)
    val idCurrency: Int? = null,
    val code: String,
    val name: String,
    val imageUrl: String

    )