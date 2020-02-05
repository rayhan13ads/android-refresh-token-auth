package com.example.refreshtokenapp.models

data class Login(
    val email: String,
    val password: String,
    val refreshToken:String
)