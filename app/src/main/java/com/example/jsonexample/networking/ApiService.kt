package com.example.jsonexample.networking

import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/posts")
    suspend fun getPosts() : Response<List<JsonElement>>
}