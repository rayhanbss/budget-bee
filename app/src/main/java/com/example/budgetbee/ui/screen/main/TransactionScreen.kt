package com.example.budgetbee.ui.screen.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.budgetbee.ui.component.NavBar
import com.example.budgetbee.ui.component.TransactionList
import com.example.budgetbee.ui.component.getTransactionItems
import com.example.budgetbee.ui.theme.Black
import com.example.budgetbee.ui.theme.White
import com.example.budgetbee.ui.theme.YellowPrimary

val DropdownBackground = White
val TextColorPrimary = Black

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionPage(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Category") }
    var categoryExpanded by remember { mutableStateOf(false) }
    val categories = listOf("Online Class", "Investment", "Salary", "Skincare", "Lunch", "House Rent", "Shopping", "Bus Ticket")

    var selectedTransactionType by remember { mutableStateOf("Transaction Type") }
    var transactionTypeExpanded by remember { mutableStateOf(false) }
    val transactionTypes = listOf("Income", "Expense")

    val transactionItems = getTransactionItems()

    Scaffold(
        bottomBar = {
            NavBar(navController = navController, currentRoute = "transaction")
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
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
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search Transaction", color = Black) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    textStyle = TextStyle(fontWeight = FontWeight.Thin, fontSize = 14.sp),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = White,
                        focusedBorderColor = YellowPrimary,
                        unfocusedBorderColor = YellowPrimary.copy(alpha = 0.7f),
                        cursorColor = Black,
                        focusedTextColor = Black,
                        unfocusedTextColor = Black
                    ),
                    singleLine = true,
                    leadingIcon = null,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Black
                        )
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
                            categories.forEach { category ->
                                DropdownMenuItem(
                                    text = { Text(category, fontSize = 14.sp) },
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
                            transactionTypes.forEach { type ->
                                DropdownMenuItem(
                                    text = { Text(type, fontSize = 14.sp) },
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
            TransactionList(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                transactions = transactionItems
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionPagePreview() {
    val navController = rememberNavController()
    TransactionPage(navController = navController)
}