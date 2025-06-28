package com.example.budgetbee.data.response

import android.media.Image
import com.example.budgetbee.util.BooleanIntAdapter
import com.google.gson.annotations.JsonAdapter
import kotlin.jvm.Transient

data class CategoryResponse (
    val id: String,
    val name: String,
    @JsonAdapter(BooleanIntAdapter::class)
    val isExpense: Boolean,
    @Transient
    val image: Image? = null,
    val userId: String
)

