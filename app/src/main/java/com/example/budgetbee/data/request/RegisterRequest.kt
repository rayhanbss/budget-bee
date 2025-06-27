package com.example.budgetbee.data.request

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val rememberMe : Boolean
)
