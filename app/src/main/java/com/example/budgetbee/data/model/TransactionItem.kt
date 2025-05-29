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
import com.example.budgetbee.TransactionItem

data class TransactionItem(
    val title: String,
    val date: String,
    val amount: String,
    val isExpense: Boolean,
    val icon: ImageVector
)

val transactions = listOf(
    TransactionItem("Online Class", "19/05/2025", "Rp 999.99,-", true, Icons.Default.School),
    TransactionItem("Investment", "19/05/2025", "Rp 999.99,-", false, Icons.Default.BarChart),
    TransactionItem("Salary", "19/05/2025", "Rp 999.99,-", true, Icons.Default.AttachMoney),
    TransactionItem("Skincare", "19/05/2025", "Rp 999.99,-", true, Icons.Default.HealthAndSafety),
    TransactionItem("Lunch", "19/05/2025", "Rp 999.99,-", true, Icons.Default.LunchDining),
    TransactionItem("House Rent", "19/05/2025", "Rp 999.99,-", false, Icons.Default.House),
    TransactionItem("Shopping", "19/05/2025", "Rp 999.99,-", true, Icons.Default.ShoppingBag),
    TransactionItem("Bus Ticket", "19/05/2025", "Rp 999.99,-", true, Icons.Default.DirectionsBus),
    TransactionItem("Online Class", "19/05/2025", "Rp 999.99,-", true, Icons.Default.School),
    TransactionItem("Investment", "19/05/2025", "Rp 999.99,-", false, Icons.Default.BarChart),
    TransactionItem("Salary", "19/05/2025", "Rp 999.99,-", true, Icons.Default.AttachMoney),
    TransactionItem("Skincare", "19/05/2025", "Rp 999.99,-", true, Icons.Default.HealthAndSafety),
    TransactionItem("Lunch", "19/05/2025", "Rp 999.99,-", true, Icons.Default.LunchDining),
    TransactionItem("House Rent", "19/05/2025", "Rp 999.99,-", false, Icons.Default.House),
)

