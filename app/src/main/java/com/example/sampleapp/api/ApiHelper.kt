package com.example.sampleapp.api

import com.example.sampleapp.models.UserResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getUsers(): Response<UserResponse>
}