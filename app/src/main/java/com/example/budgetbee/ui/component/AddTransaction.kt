package com.example.budgetbee.ui.component

import android.media.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.budgetbee.ui.theme.White

@Composable
fun AddTransaction() {
    var name by remember { mutableStateOf("") }
    var categoryId by remember { mutableStateOf("") }
    var targetId by remember { mutableStateOf("") }
    var isSaving by remember { mutableStateOf(false) }
    var dateTransaction by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    var categoryList = listOf("Food", "Transport", "Shopping", "Create new category...")
    var selectedCategory by remember { mutableStateOf(categoryList[0]) }
    var isDropdownExpanded by remember { mutableStateOf(false) }
    var isCreatingCategory by remember { mutableStateOf(false) }
    var newCategoryName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .shadow(2.dp, RoundedCornerShape(12.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            label = { Text("Transaction Name") },
            value = name,
            onValueChange = { name = it },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (!isCreatingCategory) {
            Box {
                OutlinedTextField(
                    value = selectedCategory,
                    onValueChange = {},
                    label = { Text("Category") },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { isDropdownExpanded = true }
                )
                DropdownMenu(
                    expanded = isDropdownExpanded,
                    onDismissRequest = { isDropdownExpanded = false }
                ) {
                    categoryList.forEach { category ->
                        DropdownMenuItem(
                            onClick = {
                                if (category == "Create new category...") {
                                    isCreatingCategory = true
                                    newCategoryName = ""
                                } else {
                                    selectedCategory = category
                                    categoryId = category // Replace with actual ID if available
                                }
                                isDropdownExpanded = false
                            },
                            text = { Text(category) },
                            trailingIcon = { Icon(Icons.Default.ArrowDropDown, contentDescription = null) },
                        )
                    }
                }
            }
        } else {
            OutlinedTextField(
                value = newCategoryName,
                onValueChange = { newCategoryName = it },
                label = { Text("New Category Name") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = {
                        isCreatingCategory = false
                        newCategoryName = ""
                    }) {
                        Icon(Icons.Default.Close, contentDescription = "Cancel")
                    }
                }
            )
        }
    }
}

data class TransactionRequest(
    val name: String,
    val categoryId: String?,
    val targetId: String?,
    val isSaving: Boolean,
    val dateTransaction: String,
    val amount: Double,
    val note: String,
    val image: Image?
)

@Preview
@Composable
fun AddTransactionPreview() {
    AddTransaction()
}

