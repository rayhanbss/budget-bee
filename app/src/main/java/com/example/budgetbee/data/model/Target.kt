package com.example.budgetbee.data.model

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Laptop
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.budgetbee.ui.theme.YellowPrimary

data class Target(
    val id: String,
    val userId: String,
    val name: String,
    val amountNeeded: Double,
    val amountCollected: Double,
    val deadline: String,
    val status: String
)
