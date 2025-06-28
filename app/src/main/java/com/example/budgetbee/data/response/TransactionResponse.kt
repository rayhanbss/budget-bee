package com.example.budgetbee.data.response

import android.media.Image
import com.example.budgetbee.util.BooleanIntAdapter
import com.google.gson.annotations.JsonAdapter

data class TransactionResponse(
    val id: Int,
    val name: String,
    val userId: Int,
    val categoryId: Int,
    val targetId: Int?,
    val isSaving: Boolean,
    val dateTransaction : String,
    val amount: Double,
    val note: String?,
    val image: Image?,
    val categoryName: String,
    @JsonAdapter(BooleanIntAdapter::class)
    val categoryIsExpense: Boolean,
    val targetName: String?
)
