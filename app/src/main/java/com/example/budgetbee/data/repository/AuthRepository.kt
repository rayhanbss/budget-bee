package com.example.budgetbee.data.repository

import android.content.Context
import com.example.budgetbee.data.model.Token
import com.example.budgetbee.data.request.LoginRequest
import com.example.budgetbee.data.remote.RetrofitClient
import com.example.budgetbee.data.request.RegisterRequest
import kotlinx.coroutines.flow.Flow

class AuthRepository(context: Context) {
    suspend fun login(email: String, password: String, rememberMe: Boolean) = RetrofitClient.apiService.login(
        LoginRequest(email, password, rememberMe))

    suspend fun register(name: String, email: String, password: String, rememberMe: Boolean) = RetrofitClient.apiService.register(
        RegisterRequest(name, email, password, rememberMe))

    suspend fun logout(token: String) = RetrofitClient.apiService.logout("Bearer $token")
}

