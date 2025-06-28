package com.example.budgetbee.ui.screen.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.budgetbee.data.model.User
import com.example.budgetbee.ui.component.TransactionRow
import com.example.budgetbee.ui.theme.Black
import com.example.budgetbee.ui.theme.White
import com.example.budgetbee.ui.theme.YellowPrimary
import com.example.budgetbee.viewmodel.TransactionViewModel

val DropdownBackground = White
val TextColorPrimary = Black

@Composable
fun TransactionScreen(
    transactionViewModel: TransactionViewModel,
    user: User?,
    token: String?,
    navController: NavHostController
) {

    LaunchedEffect(user, token) {
        if (user != null && token != null) {
            Log.i("TransactionScreen", "Calling getAllTransactions with user: ${user.id}, token available: ${token.isNotBlank()}")
            transactionViewModel.getAllTransactions(user, token)
        } else {
            Log.w("TransactionScreen", "User or token is null - user: $user, token: $token")
        }
    }

    val isLoading = transactionViewModel.isLoading
    val transactionList = transactionViewModel.transactions
    val errorMessage = transactionViewModel.errorMessage

    Log.i("TransactionScreen", "Transaction list size: ${transactionList.size}")
    Log.i("TransactionScreen", "Is loading: $isLoading")
    Log.i("TransactionScreen", "Error message: $errorMessage")

    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Category") }
    var categoryExpanded by remember { mutableStateOf(false) }
    val categories = listOf("Education", "Investment", "Salary", "Healthcare", "Food", "Bill", "Shopping", "Transport")

    var selectedTransactionType by remember { mutableStateOf("Transaction Type") }
    var transactionTypeExpanded by remember { mutableStateOf(false) }
    val transactionTypes = listOf("Income", "Expense")

    val filteredTransactions = transactionList
        .filter { item ->
            val matchesQuery = searchQuery.isBlank() || item.name.contains(searchQuery, ignoreCase = true)
            val matchesCategory = selectedCategory == "Category" || item.categoryName == selectedCategory
            val matchesType = selectedTransactionType == "Transaction Type" ||
                    (selectedTransactionType == "Income" && !item.categoryIsExpense) ||
                    (selectedTransactionType == "Expense" && item.categoryIsExpense)
            matchesQuery && matchesCategory && matchesType
        }
        .sortedByDescending { it.dateTransaction }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(2.dp, shape = RoundedCornerShape(12.dp))
                .background(White),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 16.dp, 16.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "My ",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Black
                )
                Text(
                    text = "Transaction",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = YellowPrimary
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 8.dp, 16.dp, 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Search Transaction") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Black,
                        focusedLabelColor = Black,
                        unfocusedLabelColor = Gray,
                        focusedTextColor = Black,
                        unfocusedTextColor = Black,
                    ),
                    singleLine = true,
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear",
                                tint = Black,
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .clickable { searchQuery = "" }
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Black
                            )
                        }
                    }
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        OutlinedButton(
                            onClick = { categoryExpanded = true },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(4.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = DropdownBackground,
                                contentColor = Black
                            ),
                            contentPadding = PaddingValues(horizontal = 8.dp)
                        ) {
                            Text(
                                selectedCategory,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Start,
                                fontSize = 12.sp
                            )
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Category")
                        }
                        DropdownMenu(
                            expanded = categoryExpanded,
                            onDismissRequest = { categoryExpanded = false },
                            modifier = Modifier
                                .background(Color.White)
                        ) {
                            DropdownMenuItem(
                                text = { Text("Reset", fontSize = 12.sp, color = Color.Black) },
                                onClick = {
                                    selectedCategory = "Category"
                                    categoryExpanded = false
                                }
                            )
                            categories.forEach { category ->
                                DropdownMenuItem(
                                    text = { Text(category, fontSize = 12.sp, color = Color.Black) },
                                    onClick = {
                                        selectedCategory = category
                                        categoryExpanded = false
                                    }
                                )
                            }
                        }
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        OutlinedButton(
                            onClick = { transactionTypeExpanded = true },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(4.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = DropdownBackground,
                                contentColor = TextColorPrimary
                            ),
                            contentPadding = PaddingValues(horizontal = 8.dp)
                        ) {
                            Text(
                                selectedTransactionType,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Start,
                                fontSize = 12.sp
                            )
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Transaction Type")
                        }
                        DropdownMenu(
                            expanded = transactionTypeExpanded,
                            onDismissRequest = { transactionTypeExpanded = false },
                            modifier = Modifier.background(Color.White)
                        ) {
                            DropdownMenuItem(
                                text = { Text("Reset", fontSize = 12.sp, color = Color.Black) },
                                onClick = {
                                    selectedTransactionType = "Transaction Type"
                                    transactionTypeExpanded = false
                                }
                            )
                            transactionTypes.forEach { type ->
                                DropdownMenuItem(
                                    text = { Text(type, fontSize = 12.sp, color = Color.Black) },
                                    onClick = {
                                        selectedTransactionType = type
                                        transactionTypeExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(filteredTransactions) { item ->
                TransactionRow(
                    item = item,
                    navController = navController,
                    user = user,
                    tokenString = token ?: "",
                    transactionViewModel = transactionViewModel,
                )
            }
        }
    }
}
