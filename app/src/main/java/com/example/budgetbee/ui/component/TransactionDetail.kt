package com.example.budgetbee.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.budgetbee.data.model.Transaction
import com.example.budgetbee.data.model.User
import com.example.budgetbee.ui.theme.Black
import com.example.budgetbee.ui.theme.Failed
import com.example.budgetbee.ui.theme.Success
import com.example.budgetbee.ui.theme.White
import com.example.budgetbee.ui.theme.YellowPrimary
import com.example.budgetbee.viewmodel.TransactionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDetail (
    transaction: Transaction,
    showBottomSheet: MutableState<Boolean>,
    navController: NavHostController,
    transactionViewModel: TransactionViewModel,
    user: User?,
    tokenString: String? = null
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row{
                Text(
                    text = "Transaction ",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Black
                )
                Text(
                    text = "Detail",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = YellowPrimary
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        navController.navigate("edit_transaction/${transaction.id}") {
                            launchSingleTop = true
                        }
                        showBottomSheet.value = false
                    },
                    modifier = Modifier
                        .size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Transaction",
                        tint = YellowPrimary
                    )
                }
                IconButton(
                    onClick = { showDeleteDialog = true },
                    modifier = Modifier
                        .size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Transaction",
                        tint = Failed
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(2.dp, shape = RoundedCornerShape(8.dp))
                    .background(White),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = transaction.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                    Text(
                        text = transaction.categoryName,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                    )
                    Text(
                        text = transaction.dateTransaction,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                    )
                }
                Icon(
                    imageVector = Icons.Default.ArrowOutward,
                    contentDescription = if (transaction.categoryIsExpense) "Expense" else "Income",
                    tint = if (transaction.categoryIsExpense) Failed else Success,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(36.dp)
                        .rotate(if (transaction.categoryIsExpense) 0f else 180f)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(2.dp, shape = RoundedCornerShape(8.dp))
                    .background(White),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Rp. ${transaction.amount},-",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            if (transaction.isSaving)
                                PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 4.dp)
                            else
                                PaddingValues(16.dp))
                    )
                if (transaction.isSaving) {
                    Text(
                        text = "Saving on ${transaction.targetName ?: "No Target"}",
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 4.dp, 16.dp, 16.dp))
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(2.dp, shape = RoundedCornerShape(8.dp))
                    .background(White),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Notes",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 16.dp , 16.dp, 4.dp)
                )
                Text(
                    text = transaction.note ?: "No notes available",
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 4.dp, 16.dp, 16.dp)
                )
            }
        }

        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                shape = RoundedCornerShape(8.dp),
                containerColor = White,
                titleContentColor = YellowPrimary,
                textContentColor = Black,
                title = { Text(text = "Confirm Delete", fontWeight = FontWeight.Bold) },
                text = { Text(text = "Are you sure you want to delete this transaction?") },
                confirmButton = {
                    TextButton(onClick = {
                        transactionViewModel.deleteTransaction(user, tokenString, transaction.id)
                        showBottomSheet.value = false
                        showDeleteDialog = false
                    }) {
                        Text("Delete", color = Failed)
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showDeleteDialog = false },
                    ) {
                        Text("Cancel", color = Black)
                    }
                }
            )
        }
    }
}
