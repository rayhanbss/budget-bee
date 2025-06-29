package com.example.budgetbee.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetbee.data.model.User
import com.example.budgetbee.data.repository.TargetRepository
import com.example.budgetbee.data.response.TargetResponse
import com.example.budgetbee.data.model.Target
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TargetViewModel (
    private val targetRepository: TargetRepository,
) : ViewModel() {
    var targets by mutableStateOf<List<Target>>(emptyList())
    private set
    var isLoading by mutableStateOf(false)
    private set
    var errorMessage by mutableStateOf<String?>(null)
    private set
    var isTargetCreated by mutableStateOf(false)
    var isTargetUpdated by mutableStateOf(false)

    fun getAllTarget(user: User?, token: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            errorMessage = null

            if (user?.id == null || token.isNullOrBlank()) {
                errorMessage = "USer ID or token is null"
                Log.e("TargetViewModel", "User ID or token is null. User: $user, Token: $token")
                isLoading = false
                return@launch
            }

            try {
                val response = targetRepository.getAllTargets(user.id.toString(), token)
                if (response.isSuccessful) {
                    val body = response.body()
                    val mappedTargets = body?.data?.map { it.toTarget() } ?: emptyList()
                    targets = mappedTargets
                    Log.i("TargetViewModel", "Targets Loaded: ${targets.size}")
                } else {
                    val errorBody = response.errorBody()?.string()
                    errorMessage = errorBody ?: "Unknown error"
                    Log.e("TargetViewModel", "Error fetching targets: $errorMessage, Status: ${response.code()}")
                }
            } catch (e: Exception) {
                errorMessage = e.message
                Log.e("TargetViewModel", "Exception fetching targets: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }

    fun createTarget(
        user: User?,
        name: String,
        amountNeeded: Double,
        deadline: String,
        token: String?
    ) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            isTargetCreated = false

            try {
                Log.i("TargetViewModel", "Creating target for user: ${user?.id}, token: $token, name:$name, amountNeeded: $amountNeeded, deadline: $deadline")
                val response = targetRepository.createTarget(
                    userId = user?.id.toString(),
                    name = name,
                    amountNeeded = amountNeeded,
                    deadline = deadline,
                    token = token?: ""
                )

                if (response.isSuccessful) {
                    val body = response.body()
                    Log.i("TargetViewModel", "Target posted successfully: $body")
                    getAllTarget(user, token)
                    isTargetCreated = true
                } else {
                    val errorBody = response.errorBody()?.string()
                    errorMessage = errorBody ?: "Unknown error"
                    Log.e("TargetViewModel", "Error posting target: $errorMessage, Status: ${response.code()}")
                }
            } catch (e: Exception) {
                errorMessage = e.message
                Log.e("TargetViewModel", "Exception posting target: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }

    fun updateTarget(
        user: User?,
        targetId: String?,
        name: String,
        amountNeeded: Double,
        deadline: String,
        token: String?
    ) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            isTargetUpdated = false

            try {
                Log.i("TargetViewModel", "Updating target for user: ${user?.id}, targetId: $targetId, token: $token, name:$name, amountNeeded: $amountNeeded, deadline: $deadline")
                val response = targetRepository.updateTarget(
                    userId = user?.id.toString(),
                    targetId = targetId ?: "",
                    name = name,
                    amountNeeded = amountNeeded,
                    deadline = deadline,
                    token = token ?: ""
                )

                if (response.isSuccessful) {
                    Log.i("TargetViewModel", "Target updated successfully")
                    Log.i("TargetViewModel", "Response body: ${response.body()}")
                    getAllTarget(user, token)
                    isTargetUpdated = true
                } else {
                    val errorBody = response.errorBody()?.string()
                    errorMessage = errorBody ?: "Unknown error"
                    Log.e("TargetViewModel", "Error updating target: $errorMessage, Status: ${response.code()}")
                }
            } catch (e: Exception) {
                errorMessage = e.message
                Log.e("TargetViewModel", "Exception updating target: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }

    fun deleteTarget(
        user: User?,
        targetId: String?,
        token: String?
    )
    {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                Log.i("TargetViewModel", "Deleting target for user: ${user}, targetId: $targetId, token: $token")
                val response = targetRepository.deleteTarget(
                    userId = user?.id.toString(),
                    targetId = targetId ?: "",
                    token = token ?: ""
                )

                if (response.isSuccessful) {
                    Log.i("TargetViewModel", "Target deleted successfully")
                    getAllTarget(user, token)
                } else {
                    val errorBody = response.errorBody()?.string()
                    errorMessage = errorBody ?: "Unknown error"
                    Log.e("TargetViewModel", "Error deleting target: $errorMessage, Status: ${response.code()}")
                }
            } catch (e: Exception) {
                errorMessage = e.message
                Log.e("TargetViewModel", "Exception deleting target: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }

}

fun TargetResponse.toTarget(): Target {
    return Target(
        id = id.toString(),
        userId = userId.toString(),
        name = name,
        amountNeeded = amountNeeded,
        amountCollected = amountCollected,
        deadline = deadline,
        status = status
    )
}

