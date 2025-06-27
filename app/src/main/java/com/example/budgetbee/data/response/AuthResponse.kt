package com.example.budgetbee.data.response

import com.example.budgetbee.data.model.User

data class AuthResponse(
    val message: String,
    val user: User,
    val token: String
)

