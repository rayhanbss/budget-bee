package com.example.budgetbee.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

data class Category(
    val id: Int,
    val name: String,
    val icon: ImageVector,
    val isExpense: Boolean,
    val userId: Int? = null
)

val categoryList: List<Category> = listOf(
    Category(id = 1, name = "Food", icon = Icons.Default.Fastfood, true),
    Category(id = 2, name = "Transport", icon = Icons.Default.DirectionsBus, true),
    Category(id = 3, name = "Shopping", icon = Icons.Default.ShoppingBag, true),
    Category(id = 4, name = "Salary", icon = Icons.Default.Payments, false),
    Category(id = 5, name = "Gift", icon = Icons.Default.GifBox, false),
    Category(id = 6, name = "Education", icon = Icons.Default.School, true),
    Category(id = 7, name = "Investment", icon = Icons.Default.BarChart, false),
    Category(id = 8, name = "Healthcare", icon = Icons.Default.LocalHospital, true),
    Category(id = 9, name = "Bill", icon = Icons.Default.Receipt, true),
    Category(id = 10, name = "Other Income", icon = Icons.Default.LocalAtm, false),
    Category(id = 11, name = "Other Expense", icon = Icons.Default.LocalAtm, true)
)
