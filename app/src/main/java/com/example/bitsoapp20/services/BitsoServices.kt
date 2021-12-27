package com.example.bitsoapp20.services

import com.example.bitsoapp20.domain.StatusAvailableBooks
import retrofit2.Response
import retrofit2.http.GET

interface BitsoServices {
    @GET(value = "available_books/")
    suspend fun getAvailableBooks(): Response<StatusAvailableBooks>
}