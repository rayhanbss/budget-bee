package com.example.budgetbee.data.remote

import com.example.budgetbee.data.model.Token
import com.example.budgetbee.data.model.User
import com.example.budgetbee.data.request.CategorRequest
import com.example.budgetbee.data.response.AuthResponse
import com.example.budgetbee.data.request.LoginRequest
import com.example.budgetbee.data.request.RegisterRequest
import com.example.budgetbee.data.request.TransactionRequest
import com.example.budgetbee.data.response.CategoryResponse
import com.example.budgetbee.data.response.GetAllTransactionResponse
import com.example.budgetbee.data.response.TransactionResponse
import com.example.budgetbee.data.response.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    //Authentication endpoints ---------------------------------------------------------------------
    @POST("login")
    @Headers("Content-Type: application/json")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<AuthResponse>

    @POST("register")
    @Headers("Content-Type: application/json")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<AuthResponse>

    @POST("logout")
    suspend fun logout(
        @Header("Authorization") token: String
    ): Response<Unit>

    @GET("users/{userId}")
    @Headers("Content-Type: application/json")
    suspend fun getUser(
        @Path("userId") userId: String,
        @Header("Authorization") token: String
    ): Response<UserResponse>

    //Transaction endpoints ------------------------------------------------------------------------
    @GET("users/{userId}/transactions")
    @Headers("Content-Type: application/json")
    suspend fun getAllTransactions(
        @Path("userId") userId: String,
        @Header("Authorization") token: String
    ): Response<GetAllTransactionResponse>

    @GET("users/{userId}/transactions/{transactionId}")
    @Headers("Content-Type: application/json")
    suspend fun getTransactionById(
        @Path("userId") userId: String,
        @Path("transactionId") transactionId: String,
        @Header("Authorization") token: String
    ): Response<TransactionResponse>

    @POST("users/{userId}/transactions")
    @Headers("Content-Type: application/json")
    suspend fun createTransaction(
        @Path("userId") userId: String,
        @Body transaction: TransactionRequest,
        @Header("Authorization") token: String
    ): Response<TransactionResponse>

    @PATCH("users/{userId}/transactions/{transactionId}")
    @Headers("Content-Type: application/json")
    suspend fun updateTransaction(
        @Path("userId") userId: String,
        @Path("transactionId") transactionId: String,
        @Body transaction: TransactionRequest,
        @Header("Authorization") token: String
    ): Response<TransactionResponse>

    @DELETE("users/{userId}/transactions/{transactionId}")
    @Headers("Content-Type: application/json")
    suspend fun deleteTransaction(
        @Path("userId") userId: String,
        @Path("transactionId") transactionId: String,
        @Header("Authorization") token: String
    ): Response<Unit>

    //Target endpoints -----------------------------------------------------------------------------
    @GET("users/{userId}/targets")

    //Category endpoints ---------------------------------------------------------------------------
    @POST("users/{userId}/categories")
    @Headers("Content-Type: application/json")
    suspend fun createCategory(
        @Path("userId") userId: String,
        @Body category: CategorRequest,
        @Header("Authorization") token: String
    ): Response<CategoryResponse>
}

