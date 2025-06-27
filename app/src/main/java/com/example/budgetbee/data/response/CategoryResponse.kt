package com.example.budgetbee.data.response

import android.media.Image

data class CategoryResponse (
    val id: String,
    val name: String,
    val isExpense: Boolean,
    val image: Image? = null,
    val userId: String
)