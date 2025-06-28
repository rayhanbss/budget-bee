package com.example.budgetbee.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.budgetbee.data.repository.TargetRepository

class TargetViewModelFactory (
    private val targetRepository: TargetRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TargetViewModel::class.java)) {
            return TargetViewModel(targetRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
