package com.example.budgetbee.data.repository

import android.content.Context
import com.example.budgetbee.data.remote.RetrofitClient
import com.example.budgetbee.data.request.CategoryRequest

class CategoryRepository(context: Context) {
    suspend fun getAllCategories(userId: String, token: String) =
        RetrofitClient.apiService.getAllCategories(userId, token)

    suspend fun createCategory(userId: String, name: String, isExpense: Boolean, token: String) =
        RetrofitClient.apiService.createCategory(userId, CategoryRequest(name, isExpense), token)
}