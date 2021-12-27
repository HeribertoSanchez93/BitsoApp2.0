package com.example.bitsoapp20.util

import android.util.Log
import retrofit2.Response

abstract class BaseRequest {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Log.e("remoteDataSource", message)
        return Resource.error("Network call has failed for a following reason: $message")
    }
     suspend fun <T> getResult2(call: suspend () -> Response<T>): T? {
        var body:T?=null
        try {
            val response = call()
            if (response.isSuccessful)
                body = response.body()
        } catch (e: Exception) {
            Log.d("message"," ${e.message}")
        }
        return body
    }
}