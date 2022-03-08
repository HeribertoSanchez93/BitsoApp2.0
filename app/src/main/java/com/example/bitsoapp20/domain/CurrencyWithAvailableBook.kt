package com.example.bitsoapp20.domain

import androidx.room.Embedded
import androidx.room.Relation

data class CurrencyWithAvailableBook (
    @Embedded val currency: Currency,
    @Relation(
        parentColumn = "idCurrency",
        entityColumn = "idAvailableBook"
    )
    val availableBooks: List<AvailableBook>
)