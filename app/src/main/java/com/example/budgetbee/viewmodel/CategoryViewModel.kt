package com.example.budgetbee.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.budgetbee.data.model.Category
import com.example.budgetbee.data.model.User
import com.example.budgetbee.data.repository.CategoryRepository
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    var categories by mutableStateOf<List<Category>>(emptyList())
        private set
    var isLoading by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set
    var isCategoryCreated by mutableStateOf(false)
        internal set

    fun getAllCategories(user: User?, token: String?) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            if (user?.id == null || token.isNullOrBlank()) {
                errorMessage = "User ID or token is null"
                isLoading = false
                return@launch
            }

            try {
                val response = categoryRepository.getAllCategories(user.id.toString(), token)
                if (response.isSuccessful) {
                    val body = response.body()
                    categories = body?.data?.map {
                        Category(
                            id = it.id,
                            name = it.name,
                            isExpense = it.isExpense,
                            userId = it.userId.toIntOrNull()
                        )
                    } ?: emptyList()
                } else {
                    errorMessage = response.errorBody()?.string() ?: "Unknown error"
                }
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }

    fun createCategory(user: User?, name: String, isExpense: Boolean, token: String?) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            isCategoryCreated = false

            if (user?.id == null || token.isNullOrBlank()) {
                errorMessage = "User ID or token is null"
                isLoading = false
                return@launch
            }

            try {
                val response = categoryRepository.createCategory(user.id.toString(), name, isExpense, token)
                if (response.isSuccessful) {
                    val body = response.body()
                    Log.i("CategoryViewModel", "Category Created: ${body?.data?.name}")
                    isCategoryCreated = true
                    getAllCategories(user, token)
                } else {
                    errorMessage = response.errorBody()?.string() ?: "Unknown error"
                }
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }
}

