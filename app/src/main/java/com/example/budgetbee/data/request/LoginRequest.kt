package com.example.budgetbee.data.request

data class LoginRequest(
    val email: String,
    val password: String,
    val rememberMe : Boolean
)