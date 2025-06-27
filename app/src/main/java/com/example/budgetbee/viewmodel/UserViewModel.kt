package com.example.budgetbee.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetbee.data.model.Token
import com.example.budgetbee.data.model.User
import com.example.budgetbee.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    private val _userState = MutableStateFlow<User?>(null)
    val userState: StateFlow<User?> = _userState

    init {
        viewModelScope.launch {
            userRepository.getUser.collect {
                _userState.value = it
            }
        }
    }

    val remoteSuccess = mutableStateOf(false)
    fun getUserRemote(userId: String, token: String) {
        viewModelScope.launch {
            try {
                Log.i("UserViewModel", "Fetching user with ID: $userId and token: $token")
                val response = userRepository.getUserRemote(userId, token)
                Log.i("UserViewModel", "Status: ${response.code()}")
                val responseBody = response.body()?.data
                val errorBodyStr = response.errorBody()?.string()
                Log.i("UserViewModel", "Raw response body: $responseBody")
                Log.i("UserViewModel", "Raw error body: $errorBodyStr")
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        Log.i("UserViewModel", "User fetched successfully: $responseBody")
                        remoteSuccess.value = true
                    } else {
                        Log.e("UserViewModel", "Response body is null despite success code")
                        remoteSuccess.value = false
                    }
                } else {
                    Log.e("UserViewModel", "Error fetching user: $errorBodyStr, Status: ${response.code()}")
                    remoteSuccess.value = false
                }
            } catch (e: Exception) {
                Log.e("UserViewModel", "Exception fetching user: ${e.message}")
                remoteSuccess.value = false
            }
        }
    }
}
