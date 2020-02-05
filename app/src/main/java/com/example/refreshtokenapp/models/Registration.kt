package com.example.refreshtokenapp.models

data class Registration(
    val category_id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val password: String,
    val repeatPassword: String
)