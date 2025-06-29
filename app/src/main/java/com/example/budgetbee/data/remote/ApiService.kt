package com.example.budgetbee.data.remote

import com.example.budgetbee.data.request.CategoryRequest
import com.example.budgetbee.data.response.AuthResponse
import com.example.budgetbee.data.request.LoginRequest
import com.example.budgetbee.data.request.RegisterRequest
import com.example.budgetbee.data.request.TargetRequest
import com.example.budgetbee.data.request.TransactionRequest
import com.example.budgetbee.data.response.GetAllCategoryResponse
import com.example.budgetbee.data.response.CategoryResponse
import com.example.budgetbee.data.response.GetAllTargetResponse
import com.example.budgetbee.data.response.GetAllTransactionResponse
import com.example.budgetbee.data.response.SingleCategoryResponse
import com.example.budgetbee.data.response.SingleTransactionResponse
import com.example.budgetbee.data.response.TargetResponse
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

    @POST("users/{userId}/transactions")
    @Headers("Content-Type: application/json")
    suspend fun createTransaction(
        @Path("userId") userId: String,
        @Body transaction: TransactionRequest,
        @Header("Authorization") token: String
    ): Response<SingleTransactionResponse>

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
    @Headers("Content-Type: application/json")
    suspend fun getAllTargets(
        @Path("userId") userId: String,
        @Header("Authorization") token: String
    ): Response<GetAllTargetResponse>

    @GET("userID/{userId}/targets")
    @Headers("Content-Type: application/json")
    suspend fun getTargetById(
        @Path("userId") userId: String,
        @Path("targetId") targetId: String,
        @Header("Authorization") token: String
    ): Response<TargetResponse>

    @POST("users/{userId}/targets")
    @Headers("Content-Type: application/json")
    suspend fun createTarget(
        @Path("userId") userId: String,
        @Body target: TargetRequest,
        @Header("Authorization") token: String
    ): Response<TargetResponse>

    @PATCH("users/{userId}/targets")
    @Headers("Content-Type: application/json")
    suspend fun updateTarget(
        @Path("userId") userId: String,
        @Path("targetId") targetId: String,
        @Body target: TargetRequest,
        @Header("Authorization") token: String
    ): Response<TargetResponse>

    @DELETE("users/{userId}/targets")
    @Headers("Content-Type: application/json")
    suspend fun deleteTarget(
        @Path("userId") userId: String,
        @Path("targetId") targetId: String,
        @Header("Authorization") token: String
    ): Response<TargetResponse>

    //Category endpoints ---------------------------------------------------------------------------
    @GET("users/{userId}/categories")
    @Headers("Content-Type: application/json")
    suspend fun getAllCategories(
        @Path("userId") userId: String,
        @Header("Authorization") token: String
    ): Response<GetAllCategoryResponse>

    @POST("users/{userId}/categories")
    @Headers("Content-Type: application/json")
    suspend fun createCategory(
        @Path("userId") userId: String,
        @Body category: CategoryRequest,
        @Header("Authorization") token: String
    ): Response<SingleCategoryResponse>
}

