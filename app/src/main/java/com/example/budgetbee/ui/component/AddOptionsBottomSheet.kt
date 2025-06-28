package com.example.budgetbee.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddOptionsBottomSheet(
    onAddTransaction: () -> Unit,
    onAddCategory: () -> Unit,
    onAddTarget: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Add New", fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onAddTransaction() }
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.AddCircle, contentDescription = "Add Transaction")
            Spacer(modifier = Modifier.width(16.dp))
            Text("Transaction")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onAddCategory() }
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Category, contentDescription = "Add Category")
            Spacer(modifier = Modifier.width(16.dp))
            Text("Category")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onAddTarget() }
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.TrackChanges, contentDescription = "Add Target")
            Spacer(modifier = Modifier.width(16.dp))
            Text("Target")
        }
    }
}

