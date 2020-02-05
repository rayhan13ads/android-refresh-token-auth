package com.example.refreshtokenapp.models

data class LoginResponse(
    val `data`: Data,
    val message: String,
    val success: Boolean
)