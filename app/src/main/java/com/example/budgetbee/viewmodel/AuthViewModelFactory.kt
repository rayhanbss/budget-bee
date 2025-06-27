package com.example.budgetbee.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.budgetbee.data.repository.AuthRepository
import com.example.budgetbee.data.repository.CategoryRepository
import com.example.budgetbee.data.repository.TokenRepository
import com.example.budgetbee.data.repository.UserRepository

class AuthViewModelFactory(
    private val repository: AuthRepository,
    private val tokenRepository: TokenRepository,
    private val categoryRepository: CategoryRepository,
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(repository, tokenRepository, categoryRepository, userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

