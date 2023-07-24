package com.example.sampleapp.api

import com.example.sampleapp.models.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users?per_page=12")
    suspend fun getUsers(): Response<UserResponse>
}