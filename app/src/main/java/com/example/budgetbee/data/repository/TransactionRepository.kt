package com.example.budgetbee.data.repository

import android.content.Context
import android.media.Image
import com.example.budgetbee.data.remote.RetrofitClient
import com.example.budgetbee.data.request.TransactionRequest

class TransactionRepository(context: Context) {
    suspend fun getAllTransactions(userId: String, token: String) =
        RetrofitClient.apiService.getAllTransactions(userId, "Bearer $token")

    suspend fun getTransactionById(userId: String, transactionId: String, token: String) =
        RetrofitClient.apiService.getTransactionById(userId, transactionId, "Bearer $token")

    suspend fun createTransaction(
        userId: String,
        name: String,
        categoryId: String?,
        targetId: String?,
        isSaving: Boolean,
        dateTransaction: String,
        amount: Double,
        note: String,
        image: Image? = null,
        token: String
    ) = RetrofitClient.apiService.createTransaction(
        userId,
        TransactionRequest(name, categoryId, targetId, isSaving, dateTransaction, amount, note, image),
        "Bearer $token"
    )

    suspend fun updateTransaction(
        userId: String,
        transactionId: String,
        name: String,
        categoryId: String?,
        targetId: String?,
        isSaving: Boolean,
        dateTransaction: String,
        amount: Double,
        note: String,
        image: Image? = null,
        token: String
    ) = RetrofitClient.apiService.updateTransaction(
        userId,
        transactionId,
        TransactionRequest(name, categoryId, targetId, isSaving, dateTransaction, amount, note, image),
        "Bearer $token"
    )

    suspend fun deleteTransaction(userId: String, transactionId: String, token: String) =
        RetrofitClient.apiService.deleteTransaction(userId, transactionId, "Bearer $token")
}
