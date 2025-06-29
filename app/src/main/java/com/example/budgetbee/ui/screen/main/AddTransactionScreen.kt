package com.example.budgetbee.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.budgetbee.data.model.Category
import com.example.budgetbee.data.model.Target
import com.example.budgetbee.data.model.User
import com.example.budgetbee.ui.theme.Black
import com.example.budgetbee.ui.theme.Grey
import com.example.budgetbee.ui.theme.White
import com.example.budgetbee.ui.theme.YellowPrimary
import com.example.budgetbee.viewmodel.TransactionViewModel

@Composable
fun AddTransactionScreen(
    navController: NavController,
    transactionViewModel: TransactionViewModel,
    user: User?,
    tokenString: String?,
    categoryList: List<Category>,
    targetList: List<Target>
) {
    val name = remember { mutableStateOf("") }
    val categoryId = remember { mutableStateOf("") }
    val targetId = remember { mutableStateOf("") }
    val amount = remember { mutableStateOf("") }
    val note = remember { mutableStateOf("") }
    var isSaving by remember { mutableStateOf(false) }
    var categoryExpanded by remember { mutableStateOf(false) }
    var targetExpanded by remember { mutableStateOf(false) }
    var selectedCategory = categoryList.find { it.id == categoryId.value }?.name ?: "Select Category"
    var selectedTarget = targetList.find { it.id == targetId.value }?.name ?: "Select Target"
    val isTransactionCreated = transactionViewModel.isTransactionCreated

    LaunchedEffect(isTransactionCreated) {
        if (isTransactionCreated) {
            navController.navigate("transaction")
            transactionViewModel.isTransactionCreated = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(White),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row {
                Text(
                    text = "Add ",
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
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "For Saving?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Black
                )
                Switch(
                    checked = isSaving,
                    onCheckedChange = {
                        isSaving = it
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = YellowPrimary,
                        checkedBorderColor = YellowPrimary,
                        checkedTrackColor = White,
                        uncheckedTrackColor = White,
                        uncheckedThumbColor = Grey
                    ),
                )
            }
            OutlinedTextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Transaction Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
            )
            OutlinedTextField(
                value = amount.value,
                onValueChange = { amount.value = it },
                label = { Text("Amount") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
            )
//            OutlinedTextField(
//                value = targetId.value,
//                onValueChange = { targetId.value = it },
//                label = { Text("Target") },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(White),
//                enabled = isSaving
//            )
            Spacer(modifier = Modifier.height(2.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedButton(
                    onClick = { targetExpanded = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    enabled = isSaving,
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = DropdownBackground,
                        contentColor = Black
                    ),
                    contentPadding = PaddingValues(horizontal = 8.dp)
                ) {
                    Text(
                        selectedTarget,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start,
                        fontSize = 14.sp,
                    )
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Target")
                }
                DropdownMenu(
                    expanded = targetExpanded,
                    onDismissRequest = { targetExpanded = false },
                    modifier = Modifier
                        .background(Color.White)
                ) {
                    targetList.forEach { target ->
                        DropdownMenuItem(
                            text = { Text(target.name, fontSize = 14.sp, color = Color.Black) },
                            onClick = {
                                selectedTarget = target.name
                                targetId.value = target.id
                                targetExpanded = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(2.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedButton(
                    onClick = { categoryExpanded = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
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
                        fontSize = 14.sp,
                    )
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Category")
                }
                DropdownMenu(
                    expanded = categoryExpanded,
                    onDismissRequest = { categoryExpanded = false },
                    modifier = Modifier
                        .background(Color.White)
                ) {
                    categoryList.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category.name, fontSize = 14.sp, color = Color.Black) },
                            onClick = {
                                selectedCategory = category.name
                                categoryId.value = category.id
                                categoryExpanded = false
                            }
                        )
                    }
                }
            }
            OutlinedTextField(
                value = note.value,
                onValueChange = { note.value = it },
                label = { Text("Note") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
            )
        }

        Button(
            onClick = {
                transactionViewModel.createTransaction(
                    userId = user?.id.toString(),
                    token = tokenString,
                    name = name.value,
                    categoryId = categoryId.value,
                    targetId = targetId.value,
                    amount = amount.value.toDoubleOrNull() ?: 0.0,
                    note = note.value,
                    isSaving = isSaving,
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = YellowPrimary,
                contentColor = White
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Transaction"
            )
            Text(
                text = "Add New Transaction",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

