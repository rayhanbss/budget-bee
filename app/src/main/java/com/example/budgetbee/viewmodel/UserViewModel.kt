package com.example.budgetbee.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetbee.data.model.User
import com.example.budgetbee.data.repository.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class DashboardViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    val user: StateFlow<User?> = userRepository.getUser
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
}