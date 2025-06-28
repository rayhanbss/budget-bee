package com.example.budgetbee.data.request

data class TransactionRequest (
    val name: String,
    val categoryId: String,
    val targetId: String? = null,
    val isSaving: Boolean = false,
    val dateTransaction: String,
    val amount: Double,
    val note: String,
    val image: String? = null
)