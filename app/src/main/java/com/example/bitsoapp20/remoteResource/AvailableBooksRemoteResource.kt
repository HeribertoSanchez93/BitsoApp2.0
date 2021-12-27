package com.example.bitsoapp20.remoteResource

import com.example.bitsoapp20.services.BitsoServices
import com.example.bitsoapp20.util.BaseRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class AvailableBooksRemoteResource  @Inject constructor(
    private val bitsoServices: BitsoServices
):BaseRequest(){
    suspend fun getAvailableBooks() = getResult2 { bitsoServices.getAvailableBooks() }
}