package com.example.sampleapp.repository

import com.example.sampleapp.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getUser() = apiHelper.getUsers()
}