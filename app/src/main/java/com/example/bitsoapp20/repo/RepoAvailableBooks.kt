package com.example.bitsoapp20.repo

import com.example.bitsoapp20.domain.AvailableBook
import com.example.bitsoapp20.local.AvailableBooksDao
import com.example.bitsoapp20.remoteResource.AvailableBooksRemoteResource
import com.example.bitsoapp20.util.performGetOperation2
import javax.inject.Inject

class RepoAvailableBooks@Inject constructor(
    private val availableBooksRemoteResource: AvailableBooksRemoteResource,
    private val availableBooksDao: AvailableBooksDao
) {
    suspend fun getAvailableBooks(): List<AvailableBook>? {
        return performGetOperation2(networkCall = { availableBooksRemoteResource.getAvailableBooks() },
            databaseQuery = { availableBooksDao.getAll() },
            saveCallResult = { availableBooksDao.insertAll(it?.payload) })
    }
}