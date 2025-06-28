package com.example.budgetbee.ui.component

import android.media.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.example.budgetbee.data.model.Category
import com.example.budgetbee.data.model.Transaction
import com.example.budgetbee.data.model.User
import com.example.budgetbee.ui.screen.main.DropdownBackground
import com.example.budgetbee.ui.theme.Black
import com.example.budgetbee.ui.theme.Grey
import com.example.budgetbee.ui.theme.White
import com.example.budgetbee.ui.theme.YellowPrimary
import com.example.budgetbee.viewmodel.TransactionViewModel

@Composable
fun EditTransactionForm(transactionViewModel: TransactionViewModel, user: User?, tokenString: String?, transaction: Transaction) {
    val dummyCategory: List<Category> =  listOf(
        Category(id = "1518", name = "test category", isExpense = true),
        Category(id = "1520", name = "korupsi", isExpense = true)
    )

    val name = remember { mutableStateOf(transaction.name) }
    val categoryId = remember { mutableStateOf(transaction.categoryId) }
    val targetId = remember { mutableStateOf(transaction.targetId) }
    val amount = remember { mutableDoubleStateOf(transaction.amount) }
    val note = remember { mutableStateOf(transaction.note) }
    var isSaving by remember { mutableStateOf(transaction.isSaving) }
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory = dummyCategory.find { it.id == categoryId.value }?.name ?: "Select Category"

    Column(
        modifier = Modifier
            .padding(16.dp)
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
            value = amount.value.toString(),
            onValueChange = { newValue ->
                amount.value = newValue.toDoubleOrNull() ?: 0.0
            },
            label = { Text("Amount") },
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
        )
        OutlinedTextField(
            value = targetId.value.toString(),
            onValueChange = { targetId.value = it },
            label = { Text("Target") },
            modifier = Modifier
                .fillMaxWidth()
                .background(White),
            enabled = isSaving
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { expanded = true },
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
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(Color.White)
            ) {
                dummyCategory.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(category.name, fontSize = 14.sp, color = Color.Black) },
                        onClick = {
                            selectedCategory = category.name
                            categoryId.value = category.id
                            expanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = note.value.toString(),
            onValueChange = { note.value = it },
            label = { Text("Note") },
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
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
                ) },
            colors = ButtonDefaults.buttonColors(
                containerColor = YellowPrimary,
                contentColor = White
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
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