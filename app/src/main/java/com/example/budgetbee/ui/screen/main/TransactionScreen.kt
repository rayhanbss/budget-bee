package com.example.budgetbee.ui.screen.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.budgetbee.R
import com.example.budgetbee.data.model.transactions
import com.example.budgetbee.ui.component.TransactionRow
import com.example.budgetbee.ui.theme.Black
import com.example.budgetbee.ui.theme.White
import com.example.budgetbee.ui.theme.YellowPrimary

val DropdownBackground = White
val TextColorPrimary = Black

@Composable
fun TransactionScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Category") }
    var categoryExpanded by remember { mutableStateOf(false) }
    val categories = listOf("Education", "Investment", "Salary", "Healthcare", "Food", "Bill", "Shopping", "Transport")

    var selectedTransactionType by remember { mutableStateOf("Transaction Type") }
    var transactionTypeExpanded by remember { mutableStateOf(false) }
    val transactionTypes = listOf("Income", "Expense")

    val filteredTransactions = transactions.filter { item ->
        val matchesQuery = searchQuery.isBlank() || item.title.contains(searchQuery, ignoreCase = true)
        val matchesCategory = selectedCategory == "Category" || item.category == selectedCategory
        val matchesType = selectedTransactionType == "Transaction Type" ||
                (selectedTransactionType == "Income" && !item.isExpense) ||
                (selectedTransactionType == "Expense" && item.isExpense)

        matchesQuery && matchesCategory && matchesType
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.header),
                contentDescription = "Header",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Transaction",
                textAlign = TextAlign.Center,
                color = YellowPrimary,
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search Transaction", color = Black) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                textStyle = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp),
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = YellowPrimary,
                    unfocusedBorderColor = YellowPrimary.copy(alpha = 0.7f),
                    focusedLabelColor = Black,
                    unfocusedLabelColor = Gray,
                    focusedTextColor = Black,
                    unfocusedTextColor = Black,
                ),
                singleLine = true,
                leadingIcon = null,
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
                    }else{
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = DropdownBackground,
                            contentColor = TextColorPrimary
                        ),
                        border = BorderStroke(1.dp, YellowPrimary),
                        contentPadding = PaddingValues(horizontal = 12.dp)
                    ) {
                        Text(
                            selectedCategory,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Start,
                            fontSize = 14.sp
                        )
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Category")
                    }
                    DropdownMenu(
                        expanded = categoryExpanded,
                        onDismissRequest = { categoryExpanded = false },
                        modifier = Modifier.background(Color.White)
                    ) {
                        DropdownMenuItem(
                            text = { Text("Reset", fontSize = 14.sp, color = Color.Black) },
                            onClick = {
                                selectedCategory = "Category"
                                categoryExpanded = false
                            }
                        )
                        categories.forEach { category ->
                            DropdownMenuItem(
                                text = { Text(category, fontSize = 14.sp, color = Color.Black) },
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = DropdownBackground,
                            contentColor = TextColorPrimary
                        ),
                        border = BorderStroke(1.dp, YellowPrimary),
                        contentPadding = PaddingValues(horizontal = 12.dp)
                    ) {
                        Text(
                            selectedTransactionType,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Start,
                            fontSize = 14.sp
                        )
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Transaction Type")
                    }
                    DropdownMenu(
                        expanded = transactionTypeExpanded,
                        onDismissRequest = { transactionTypeExpanded = false },
                        modifier = Modifier.background(Color.White)
                    ) {
                        DropdownMenuItem(
                            text = { Text("Reset", fontSize = 14.sp, color = Color.Black) },
                            onClick = {
                                selectedTransactionType = "Transaction Type"
                                categoryExpanded = false
                            }
                        )
                        transactionTypes.forEach { type ->
                            DropdownMenuItem(
                                text = { Text(type, fontSize = 14.sp, color = Color.Black) },
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
        LazyColumn {
            items(filteredTransactions) { item ->
                TransactionRow(item = item)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionPagePreview() {
    TransactionScreen()
}