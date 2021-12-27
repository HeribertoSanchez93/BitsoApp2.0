package com.example.bitsoapp20.repo

import com.example.bitsoapp20.domain.AvailableBook
import com.example.bitsoapp20.remoteResource.AvailableBooksRemoteResource
import com.example.bitsoapp20.util.performGetOperation2
import javax.inject.Inject

class RepoAvailableBooks@Inject constructor(
    private val availableBooksRemoteResource: AvailableBooksRemoteResource
) {
    suspend fun getAvailableBooks():List<AvailableBook>?{
        val availableBooks= performGetOperation2(networkCall={availableBooksRemoteResource.getAvailableBooks()})
        return availableBooks?.payload
    }
}