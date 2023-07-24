package com.example.sampleapp.models

data class UserResponse(
    val data: List<User>? = null,
    val status: String? = ""
)