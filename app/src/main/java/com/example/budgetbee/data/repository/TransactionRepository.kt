package com.example.budgetbee.data.repository

import android.content.Context
import com.example.budgetbee.data.remote.RetrofitClient
import com.example.budgetbee.data.request.TransactionRequest
import java.time.LocalDate

class TransactionRepository(context: Context) {
    suspend fun getAllTransactions(userId: String, token: String) =
        RetrofitClient.apiService.getAllTransactions(userId, "Bearer $token")

    suspend fun createTransaction(
        userId: String,
        name: String,
        categoryId: String,
        targetId: String? = null,
        isSaving: Boolean,
        amount: Double,
        note: String,
        token: String
    ) = RetrofitClient.apiService.createTransaction(
        userId,
        TransactionRequest(
            name = name,
            categoryId = categoryId,
            targetId = targetId,
            isSaving = isSaving,
            dateTransaction = LocalDate.now().toString(),
            amount = amount,
            note = note,
            image = null,
        ),
        "Bearer $token"
    )

    suspend fun updateTransaction(
        userId: String,
        transactionId: String,
        name: String,
        categoryId: String,
        targetId: String? = null,
        dateTransaction: String,
        isSaving: Boolean,
        amount: Double,
        note: String,
        token: String
    ) = RetrofitClient.apiService.updateTransaction(
        userId,
        transactionId = transactionId,
        TransactionRequest(
            name = name,
            categoryId = categoryId,
            targetId = targetId,
            isSaving = isSaving,
            amount = amount,
            note = note,
            image = null,
            dateTransaction = dateTransaction,
        ),
        "Bearer $token"
    )

    suspend fun deleteTransaction(userId: String, transactionId: String, token: String) =
        RetrofitClient.apiService.deleteTransaction(userId, transactionId, "Bearer $token")

    suspend fun getAllIncome(userId: String, token: String) =
        RetrofitClient.apiService.getAllIncome(userId,  "Bearer $token")

    suspend fun getAllExpense(userId: String, token: String) =
        RetrofitClient.apiService.getAllExpense(userId,  "Bearer $token")
}
