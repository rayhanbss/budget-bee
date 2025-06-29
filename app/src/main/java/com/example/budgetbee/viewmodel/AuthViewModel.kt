package com.example.budgetbee.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetbee.data.model.Token
import com.example.budgetbee.data.response.AuthResponse
import com.example.budgetbee.data.repository.AuthRepository
import com.example.budgetbee.data.repository.CategoryRepository
import com.example.budgetbee.data.repository.TokenRepository
import com.example.budgetbee.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers

class AuthViewModel (
        private val authRepository: AuthRepository,
        private val tokenRepository: TokenRepository,
        private val userRepository: UserRepository
    ): ViewModel() {

    val loginResult = MutableStateFlow<Result<AuthResponse>?>(null)
    val emailError = MutableStateFlow<String?>(null)
    val passwordError = MutableStateFlow<String?>(null)
    val generalError = MutableStateFlow<String?>(null)
    val logoutSuccess = MutableStateFlow(false)

    private val _tokenState = MutableStateFlow<Token?>(null)
    val tokenState: StateFlow<Token?> = _tokenState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            tokenRepository.getToken.collect {
                _tokenState.value = it
            }
        }
    }

    fun login(email: String, password: String, rememberMe: Boolean){
        viewModelScope.launch(Dispatchers.IO){
            // Reset errors before login
            emailError.value = null
            passwordError.value = null
            generalError.value = null
            try {
                val response = authRepository.login(email, password, rememberMe)
                if (response.isSuccessful) {
                    response.body()?.let {
                        // Debug: Log user object before saving
                        Log.i("AuthViewModel", "Login successful: ${it.user}, ${Token(it.token)}")
                        // Save token and user to DataStore
                        tokenRepository.saveToken(Token(it.token))
                        userRepository.saveUser(it.user)
                        loginResult.value = Result.success(it)
                        // Log the token directly from the state
                        Log.i("AuthViewModel", "Token loaded: ${_tokenState.value}")
                    } ?: run {
                        generalError.value = "Empty response body"
                        loginResult.value = Result.failure(Exception("Empty response body"))
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    var errorMessage = "Login failed"
                    errorBody?.let {
                        try {
                            val json = org.json.JSONObject(it)
                            val message = json.get("message")
                            if (message is org.json.JSONObject) {
                                // Validation errors
                                if (message.has("email")) {
                                    val arr = message.getJSONArray("email")
                                    emailError.value = (0 until arr.length()).joinToString(", ") { i -> arr.getString(i) }
                                }
                                if (message.has("password")) {
                                    val arr = message.getJSONArray("password")
                                    passwordError.value = (0 until arr.length()).joinToString(", ") { i -> arr.getString(i) }
                                }
                                // Any other field errors
                                val otherKeys = message.keys().asSequence().filter { it != "email" && it != "password" }
                                val otherMsgs = otherKeys.flatMap { key ->
                                    val arr = message.getJSONArray(key)
                                    (0 until arr.length()).map { i -> arr.getString(i) }
                                }.toList()
                                if (otherMsgs.isNotEmpty()) {
                                    generalError.value = otherMsgs.joinToString(", ")
                                }
                                errorMessage = listOfNotNull(emailError.value, passwordError.value, generalError.value).joinToString(", ")
                            } else if (message is String) {
                                generalError.value = message
                                errorMessage = message
                            } else {
                                generalError.value = message.toString()
                                errorMessage = message.toString()
                            }
                        } catch (e: Exception) {
                            generalError.value = it
                            errorMessage = it
                        }
                    }
                    loginResult.value = Result.failure(Exception(errorMessage))
                }
            } catch (e: Exception) {
                generalError.value = e.message
                loginResult.value = Result.failure(e)
            }
        }
    }

    fun register(name: String, email: String, password: String, rememberMe: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            // Reset errors before registration
            emailError.value = null
            passwordError.value = null
            generalError.value = null
            try {
                val response = authRepository.register(name, email, password, rememberMe)
                if (response.isSuccessful) {
                    response.body()?.let {
                        val user = it.user
                        val token = it.token
                        loginResult.value = Result.success(it)
                        tokenRepository.saveToken(Token(token))
                        userRepository.saveUser(it.user)
                        Log.i("AuthViewModel", "Registration successful: ${it.user}, ${Token(it.token)}")
                    } ?: run {
                        generalError.value = "Empty response body"
                        loginResult.value = Result.failure(Exception("Empty response body"))
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    var errorMessage = "Registration failed"
                    errorBody?.let {
                        try {
                            val json = org.json.JSONObject(it)
                            val message = json.get("message")
                            if (message is org.json.JSONObject) {
                                // Validation errors
                                if (message.has("email")) {
                                    val arr = message.getJSONArray("email")
                                    emailError.value =
                                        (0 until arr.length()).joinToString(", ") { i ->
                                            arr.getString(i)
                                        }
                                }
                                if (message.has("password")) {
                                    val arr = message.getJSONArray("password")
                                    passwordError.value =
                                        (0 until arr.length()).joinToString(", ") { i ->
                                            arr.getString(i)
                                        }
                                }
                                // Any other field errors
                                val otherKeys = message.keys().asSequence()
                                    .filter { it != "email" && it != "password" }
                                val otherMsgs = otherKeys.flatMap { key ->
                                    val arr = message.getJSONArray(key)
                                    (0 until arr.length()).map { i -> arr.getString(i) }
                                }.toList()
                                if (otherMsgs.isNotEmpty()) {
                                    generalError.value = otherMsgs.joinToString(", ")
                                }
                                errorMessage = listOfNotNull(
                                    emailError.value,
                                    passwordError.value,
                                    generalError.value
                                ).joinToString(", ")
                            } else if (message is String) {
                                generalError.value = message
                                errorMessage = message
                            } else {
                                generalError.value = message.toString()
                                errorMessage = message.toString()
                            }
                        } catch (e: Exception) {
                            generalError.value = it
                            errorMessage = it
                        }
                    }
                    loginResult.value = Result.failure(Exception(errorMessage))
                }
            } catch (e: Exception) {
                generalError.value = e.message
                loginResult.value = Result.failure(e)
            }
        }
        }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val tokenValue = _tokenState.value?.value
                Log.i("AuthViewModel", "Logging out with token: $tokenValue")
                val response = authRepository.logout(tokenValue.toString())
                if (response.isSuccessful) {
                    // Clear token and user data
                    tokenRepository.clearToken()
                    userRepository.clearUser()
                    Log.i("AuthViewModel", "Logout successful")
                    logoutSuccess.value = true
                } else {
                    Log.e("AuthViewModel", "Logout failed: ${response.errorBody()?.string()}")
                    logoutSuccess.value = false
                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Logout error: ${e.message}")
                logoutSuccess.value = false
            }
        }
    }

}

