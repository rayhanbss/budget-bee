package com.example.budgetbee.data.model

import android.media.Image

data class Transaction(
    val id: String,
    val name: String,
    val userId: String,
    val categoryId: String?,
    val targetId: String?,
    val isSaving: Boolean,
    val dateTransaction : String,
    val amount: Double,
    val note: String,
    val image: Image?,
    val categoryName: String,
    val categoryIsExpense: Boolean,
    val targetName: String?
)