package com.example.budgetbee.data.model

data class Category(
    val id: String,
    val name: String,
    val isExpense: Boolean,
    val userId: Int? = null
)
