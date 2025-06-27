package com.example.budgetbee.data.response

import com.example.budgetbee.data.model.User
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val data: User
)

