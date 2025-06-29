package com.example.budgetbee.data.repository

import android.content.Context
import com.example.budgetbee.data.remote.RetrofitClient
import com.example.budgetbee.data.request.TargetRequest

class TargetRepository (context: Context){
    suspend fun getAllTargets(userId: String, token: String) =
        RetrofitClient.apiService.getAllTargets(userId, "Bearer $token")

    suspend fun getTargetById (userid: String, token: String, targetId: String) =
        RetrofitClient.apiService.getTargetById(userid, "Bearer $token", targetId)

    suspend fun createTarget(
        userid: String,
        name: String,
        amountNeeded: Double,
        amountCollected: Double,
        deadline: String,
        status: String,
        token: String
    ) = RetrofitClient.apiService.createTarget(
        userid,
        TargetRequest(name, amountNeeded, amountCollected, deadline, status),
        "Baerer $token"
    )

    suspend fun updateTarget(
        userid: String,
        targetId: String,
        name: String,
        amountNeeded: Double,
        amountCollected: Double,
        deadline: String,
        status: String,
        token: String
    ) = RetrofitClient.apiService.updateTarget(
        userid, targetId, TargetRequest(name, amountNeeded, amountCollected, deadline, status),
        "Baerer $token"
    )

    suspend fun deleteTarget(userid: String, targetId: String, token: String) =
        RetrofitClient.apiService.deleteTarget(userid, targetId, "Baerer $token")
}