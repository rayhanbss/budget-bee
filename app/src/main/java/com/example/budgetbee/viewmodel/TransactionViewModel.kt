package com.example.budgetbee.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.budgetbee.data.repository.TransactionRepository
import androidx.lifecycle.viewModelScope
import com.example.budgetbee.data.model.User
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.budgetbee.data.model.Transaction
import com.example.budgetbee.data.response.TransactionResponse
import kotlinx.coroutines.launch

class TransactionViewModel(
    private val transactionRepository: TransactionRepository,
) : ViewModel() {
    var transactions by mutableStateOf<List<Transaction>>(emptyList())
        private set
    var isLoading by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun getAllTransactions(user: User?, token: String?) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            if (user?.id == null || token.isNullOrBlank()) {
                errorMessage = "User ID or token is null"
                Log.e("TransactionViewModel", "User ID or token is null. User: $user, Token: $token")
                isLoading = false
                return@launch
            }

            try {
                val response = transactionRepository.getAllTransactions(user.id.toString(), token)
                if (response.isSuccessful) {
                    val body = response.body()
                    val mappedTransactions = body?.data?.map { it.toTransaction() } ?: emptyList()
                    transactions = mappedTransactions
                    Log.i("TransactionViewModel", "Transaction Loaded: ${transactions.size}")
                } else {
                    val errorBody = response.errorBody()?.string()
                    errorMessage = errorBody ?: "Unknown error"
                    Log.e("TransactionViewModel", "Error fetching transactions: $errorMessage, Status: ${response.code()}")
                }
            } catch (e: Exception) {
                errorMessage = e.message
                Log.e("TransactionViewModel", "Exception fetching transactions: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }


}

fun TransactionResponse.toTransaction(): Transaction {
    return Transaction(
        id = id.toString(),
        name = name,
        userId = userId.toString(),
        categoryId = categoryId?.toString(),
        targetId = targetId?.toString(),
        isSaving = isSaving,
        dateTransaction = dateTransaction,
        amount = amount,
        note = note,
        image = image,
        categoryName = categoryName,
        categoryIsExpense = categoryIsExpense,
        targetName = targetName
    )
}

