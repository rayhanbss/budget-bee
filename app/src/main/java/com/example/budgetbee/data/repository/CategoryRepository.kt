package com.example.budgetbee.data.repository

import android.content.Context
import com.example.budgetbee.data.remote.RetrofitClient
import com.example.budgetbee.data.request.CategorRequest

class CategoryRepository(context: Context) {
    suspend fun createCategory(userId: String, name: String, isExpense: Boolean, token: String) =
        RetrofitClient.apiService.createCategory(userId, CategorRequest(name, isExpense), token)
}