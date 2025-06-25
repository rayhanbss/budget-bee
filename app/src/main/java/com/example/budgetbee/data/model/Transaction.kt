package com.example.budgetbee.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.LunchDining
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.ui.graphics.vector.ImageVector

data class TransactionItem(
    val title: String,
    val category: String,
    val date: String,
    val amount: String,
    val isExpense: Boolean,
    val icon: ImageVector
)

val transactions = listOf(
    TransactionItem("Online Class", "Education", "19/05/2025", "Rp 999.99,-", true, Icons.Default.School),
    TransactionItem("Investment", "Investment", "19/05/2025", "Rp 999.99,-", false, Icons.Default.BarChart),
    TransactionItem("Salary", "Salary", "19/05/2025", "Rp 999.99,-", true, Icons.Default.AttachMoney),
    TransactionItem("Skincare", "Healthcare", "19/05/2025", "Rp 999.99,-", true, Icons.Default.HealthAndSafety),
    TransactionItem("Lunch", "Food", "19/05/2025", "Rp 999.99,-", true, Icons.Default.LunchDining),
    TransactionItem("House Rent", "Bill", "19/05/2025", "Rp 999.99,-", false, Icons.Default.House),
    TransactionItem("Shopping", "Shopping", "19/05/2025", "Rp 999.99,-", true, Icons.Default.ShoppingBag),
    TransactionItem("Bus Ticket", "Transport", "19/05/2025", "Rp 999.99,-", true, Icons.Default.DirectionsBus),
    TransactionItem("Online Class", "Education", "19/05/2025", "Rp 999.99,-", true, Icons.Default.School),
    TransactionItem("Investment", "Investment", "19/05/2025", "Rp 999.99,-", false, Icons.Default.BarChart),
    TransactionItem("Salary", "Salary", "19/05/2025", "Rp 999.99,-", true, Icons.Default.AttachMoney),
    TransactionItem("Skincare", "Healthcare", "19/05/2025", "Rp 999.99,-", true, Icons.Default.HealthAndSafety),
    TransactionItem("Lunch", "Food", "19/05/2025", "Rp 999.99,-", true, Icons.Default.LunchDining),
    TransactionItem("House Rent", "Bill", "19/05/2025", "Rp 999.99,-", false, Icons.Default.House),
)

