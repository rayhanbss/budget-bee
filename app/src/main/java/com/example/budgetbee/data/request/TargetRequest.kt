package com.example.budgetbee.data.request

data class TargetRequest (
    val name: String,
    val amountNeeded: Double,
    val amountCollected: Double,
    val deadline: String,
    val status: String
)