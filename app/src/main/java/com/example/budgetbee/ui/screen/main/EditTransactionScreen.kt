package com.example.budgetbee.ui.screen.main

import android.util.Log
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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
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
import com.example.budgetbee.data.model.Transaction
import com.example.budgetbee.data.model.User
import com.example.budgetbee.ui.theme.Black
import com.example.budgetbee.ui.theme.Grey
import com.example.budgetbee.ui.theme.White
import com.example.budgetbee.ui.theme.YellowPrimary
import com.example.budgetbee.viewmodel.TransactionViewModel

@Composable
fun EditTransactionScreen(
    navController: NavController,
    transactionViewModel: TransactionViewModel,
    user: User?,
    tokenString: String?,
    transaction: Transaction,
    categoryList: List<Category>,
    targetList: List<Target>,
) {
    val name = remember { mutableStateOf(transaction.name) }
    val categoryId = remember { mutableStateOf(transaction.categoryId) }
    val targetId = remember { mutableStateOf(transaction.targetId) }
    val amount = remember { mutableDoubleStateOf(transaction.amount) }
    val note = remember { mutableStateOf(transaction.note) }
    var isSaving by remember { mutableStateOf(transaction.isSaving) }
    var categoryExpanded by remember { mutableStateOf(false) }
    var targetExpanded by remember { mutableStateOf(false) }
    var selectedCategory = categoryList.find { it.id == categoryId.value }?.name ?: "Select Category"
    var selectedTarget = targetList.find { it.id == targetId.value }?.name ?: "Select Target"
    val isTransactionUpdated = transactionViewModel.isTransactionUpdated

    LaunchedEffect(isTransactionUpdated) {
        if (isTransactionUpdated) {
            navController.navigate("transaction")
            transactionViewModel.isTransactionUpdated = false
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
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row {
                Text(
                    text = "Edit ",
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
                        if( it) {
                            selectedTarget = "Select Target"
                            targetId.value = ""
                        } else {
                            selectedTarget = "No Target"
                            targetId.value = ""
                        }
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
                value = amount.doubleValue.toString(),
                onValueChange = { newValue ->
                    amount.doubleValue = newValue.toDoubleOrNull() ?: 0.0
                },
                label = { Text("Amount") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
            )
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
                value = note.value.toString(),
                onValueChange = { note.value = it },
                label = { Text("Note") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
            )
        }

        Button(
            onClick = {
                Log.i("EditTransactionScreen", "Updating transaction. $isSaving, ${targetId.value}" )
                transactionViewModel.updateTransaction(
                    userId = user?.id.toString(),
                    transactionId = transaction.id,
                    name = name.value,
                    categoryId = categoryId.value.toString(),
                    targetId = targetId.value,
                    amount = amount.doubleValue,
                    note = note.value.toString(),
                    isSaving = isSaving,
                    dateTransaction = transaction.dateTransaction,
                    token = tokenString
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = YellowPrimary,
                contentColor = White
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Update Transaction"
            )
            Text(
                text = "Update Transaction",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

