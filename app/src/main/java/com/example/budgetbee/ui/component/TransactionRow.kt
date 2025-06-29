package com.example.budgetbee.ui.component

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.budgetbee.data.model.Transaction
import com.example.budgetbee.ui.theme.Black
import com.example.budgetbee.ui.theme.White
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.ui.draw.rotate
import androidx.navigation.NavHostController
import com.example.budgetbee.data.model.User
import com.example.budgetbee.ui.theme.Failed
import com.example.budgetbee.ui.theme.Success
import com.example.budgetbee.util.CurrencyUtils
import com.example.budgetbee.viewmodel.TransactionViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionRow(
    item: Transaction,
    navController: NavHostController,
    transactionViewModel: TransactionViewModel,
    user: User?,
    tokenString: String? = null
) {
    val showBottomSheet = remember { mutableStateOf(false) }

    if (showBottomSheet.value) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet.value = false },
            containerColor = White
        ) {
            TransactionDetail(
                transaction = item,
                showBottomSheet = showBottomSheet,
                navController = navController,
                transactionViewModel = transactionViewModel,
                user = user,
                tokenString = tokenString
            )
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        showBottomSheet.value = true
                    }
                )
            },
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column{
                    Text(
                        text = item.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Black
                        )
                    Text(
                        text = item.dateTransaction,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Black
                    )
                }
            }


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Text(
                    text = CurrencyUtils.format(item.amount),
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Black
                )
                Icon(
                    imageVector = Icons.Default.ArrowOutward,
                    contentDescription = if (item.categoryIsExpense) "Expense" else "Income",
                    tint = if (item.categoryIsExpense) Failed else Success,
                    modifier =
                        if (item.categoryIsExpense) Modifier.size(32.dp)
                        else Modifier.size(32.dp).rotate(180f)
                )
            }
        }
    }
}