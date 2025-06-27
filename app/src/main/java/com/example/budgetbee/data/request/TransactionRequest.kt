package com.example.budgetbee.data.request

import android.media.Image

data class TransactionRequest (
    val name: String,
    val categoryId: String?,
    val targetId: String?,
    val isSaving: Boolean,
    val dateTransaction : String,
    val amount: Double,
    val note: String,
    val image: Image?
)