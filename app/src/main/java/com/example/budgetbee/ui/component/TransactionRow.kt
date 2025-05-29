package com.example.budgetbee.ui.component

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.budgetbee.data.model.TransactionItem
import com.example.budgetbee.ui.theme.Black
import com.example.budgetbee.ui.theme.Failed
import com.example.budgetbee.ui.theme.Success
import com.example.budgetbee.ui.theme.YellowPrimary
import com.example.budgetbee.ui.theme.YellowTertiary

@Composable
fun TransactionRow(modifier: Modifier = Modifier, item: TransactionItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp, 4.dp),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.dp, YellowPrimary),
        colors = CardDefaults.cardColors(containerColor = YellowTertiary)
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
                    imageVector = item.icon,
                    contentDescription = item.title,
                    tint = YellowPrimary
                )
                Column{
                    Text(
                        text = item.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Black
                        )
                    Text(
                        text = item.date,
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
                    text = item.amount,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Black
                )
                Icon(
                    imageVector = Icons.Default.ArrowOutward,
                    contentDescription = if (item.isExpense) "Expense" else "Income",
                    tint = if (item.isExpense) Failed else Success,
                    modifier = if (item.isExpense) Modifier.size(32.dp) else Modifier.size(32.dp).rotate(180f)
                )
            }
        }
    }
}

@Preview
@Composable
fun TransactionRowPreview() {
    val sampleItem = TransactionItem(
        title = "Lunch",
        category = "Food",
        date = "19/05/2025",
        amount = "Rp 999.99,-",
        isExpense = false,
        icon = Icons.Default.LunchDining
    )
    TransactionRow(item = sampleItem)
}