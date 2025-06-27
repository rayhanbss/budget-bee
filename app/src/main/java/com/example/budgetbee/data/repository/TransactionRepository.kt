package com.example.budgetbee.data.repository

import android.media.Image
import com.example.budgetbee.data.remote.RetrofitClient
import com.example.budgetbee.data.request.TransactionRequest

class TransactionRepository {
    suspend fun getAllTransactions(userId: String, token: String) =
        RetrofitClient.apiService.getAllTransactions(userId, token)

    suspend fun getTransactionById(userId: String, transactionId: String, token: String) =
        RetrofitClient.apiService.getTransactionById(userId, transactionId, token)

    suspend fun createTransaction(
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
    ) = RetrofitClient.apiService.createTransaction(
        userId,
        TransactionRequest(name, categoryId, targetId, isSaving, dateTransaction, amount, note, image),
        token
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
        token
    )

    suspend fun deleteTransaction(userId: String, transactionId: String, token: String) =
        RetrofitClient.apiService.deleteTransaction(userId, transactionId, token)
}
