package com.example.budgetbee.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int? = null,
    val name: String? = null,
    val email: String? = null
)
