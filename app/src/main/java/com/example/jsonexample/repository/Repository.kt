package com.example.jsonexample.repository

import com.example.jsonexample.networking.RetrofitInstance
import com.google.gson.JsonElement

class Repository {

    suspend fun getPosts() : Result<List<JsonElement>> {
        return try {
            val response  = RetrofitInstance.apiService.getPosts()
            if (response.isSuccessful) {
                val body = response.body()
                if(body!=null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("Empty response body"))
                }
            } else {
                Result.failure(Exception("Error: ${response.code()} ${response.message()}"))
            }
        } catch (exception : Exception) {
            Result.failure(exception)
        }
    }
}