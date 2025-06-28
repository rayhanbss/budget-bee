package com.example.budgetbee.data.response

data class TargetResponse (
    val id: Int,
    val userId: Int,
    val name: String,
    val amountNeeded: Double,
    val amountCollected: Double,
    val deadline: String,
    val status: String
)