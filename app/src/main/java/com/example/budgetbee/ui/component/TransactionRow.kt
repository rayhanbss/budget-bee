package com.example.budgetbee.ui.component

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.LunchDining
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.budgetbee.data.model.Transaction
import com.example.budgetbee.ui.theme.Black
import com.example.budgetbee.ui.theme.Failed
import com.example.budgetbee.ui.theme.Success
import com.example.budgetbee.ui.theme.White
import com.example.budgetbee.ui.theme.YellowPrimary
import com.example.budgetbee.ui.theme.YellowTertiary

@Composable
fun TransactionRow(modifier: Modifier = Modifier, item: Transaction) {
    Card(
        modifier = Modifier
            .fillMaxWidth().
            pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        // TODO: Do something on hold
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
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    imageVector = Icons.Default.LunchDining,
                    contentDescription = item.name,
                    tint = YellowPrimary
                )
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
                    text = item.amount.toString(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Black
                )
//                Icon(
//                    imageVector = Icons.Default.ArrowOutward,
//                    contentDescription = if (item.isExpense) "Expense" else "Income",
//                    tint = if (item.isExpense) Failed else Success,
//                    modifier = if (item.isExpense) Modifier.size(32.dp) else Modifier.size(32.dp).rotate(180f)
//                )
            }
        }
    }
}

@Preview
@Composable
fun TransactionRowPreview() {
    TransactionRow(
        item = Transaction(
            id = "1",
            name = "Groceries",
            categoryId = "2",
            isSaving = false,
            dateTransaction = "2024-06-01",
            amount = 50.0,
            note = "Weekly groceries",
            targetId = null,
            userId = "1",
            image = null,
            categoryName = "test",
            categoryIsExpense = true,
            targetName = "test"
        )
    )
}