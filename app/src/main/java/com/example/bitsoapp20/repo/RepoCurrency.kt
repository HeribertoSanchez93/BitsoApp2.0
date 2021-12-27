package com.example.bitsoapp20.repo

import com.example.bitsoapp20.domain.AvailableBook
import com.example.bitsoapp20.domain.Currency
import com.example.bitsoapp20.util.getCurrencies
import javax.inject.Inject

class RepoCurrency @Inject constructor(){
    fun getCurrencies(availableBook: List<AvailableBook>?): List<Currency> {
        return availableBook.getCurrencies()
    }
}