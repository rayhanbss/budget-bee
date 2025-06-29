package com.example.budgetbee.data.repository

import android.content.Context
import com.example.budgetbee.data.remote.RetrofitClient
import com.example.budgetbee.data.request.TargetRequest

class TargetRepository (context: Context){
    suspend fun getAllTargets(userId: String, token: String) =
        RetrofitClient.apiService.getAllTargets(userId, "Bearer $token")

    suspend fun getTargetById (userId: String, token: String, targetId: String) =
        RetrofitClient.apiService.getTargetById(userId, "Bearer $token", targetId)

    suspend fun createTarget(
        userId: String,
        name: String,
        amountNeeded: Double,
        deadline: String,
        token: String
    ) = RetrofitClient.apiService.createTarget(
        userId,
        TargetRequest(name, amountNeeded, deadline),
        "Baerer $token"
    )

    suspend fun updateTarget(
        userId: String,
        targetId: String,
        name: String,
        amountNeeded: Double,
        amountCollected: Double,
        deadline: String,
        status: String,
        token: String
    ) = RetrofitClient.apiService.updateTarget(
        userId, targetId, TargetRequest(name, amountNeeded, deadline),
        "Baerer $token"
    )

    suspend fun deleteTarget(userId: String, targetId: String, token: String) =
        RetrofitClient.apiService.deleteTarget(userId, targetId, "Baerer $token")
}