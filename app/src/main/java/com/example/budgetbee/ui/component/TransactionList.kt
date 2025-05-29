package com.example.budgetbee.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CallReceived
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.budgetbee.R
import com.example.budgetbee.ui.theme.White
import kotlin.collections.listOf

data class TransactionItem (
    val title: String,
    val date: String,
    val amount: String,
    val isExpense: Boolean,
    val icon: Painter
)

@Composable
fun getTransactionItems() : List<TransactionItem> {
    return listOf(
        TransactionItem("Online Class", "19/05/2025", "Rp 999.99,-", true, (painterResource(R.drawable.book))),
        TransactionItem("Invesment", "19/05/2025", "Rp 999.99,-", false, painterResource(R.drawable.finance)),
        TransactionItem("Salary", "19/05/2025", "Rp 999.99,-", false, painterResource(R.drawable.local_atm)),
        TransactionItem("Skincare", "19/05/2025", "Rp 999.99,-", true, painterResource(R.drawable.ecg_heart)),
        TransactionItem("Lunch", "19/05/2025", "Rp 999.99,-", true, painterResource(R.drawable.lunch_dining)),
        TransactionItem("House Rent", "19/05/2025", "Rp 999.99,-", true, painterResource(R.drawable.receipt)),
        TransactionItem("Shopping", "19/05/2025", "Rp 999.99,-", true, painterResource(R.drawable.shopping_bag)),
        TransactionItem("Bus Ticket", "19/05/2025", "Rp 999.99,-", true, painterResource(R.drawable.directions_car))
    )
}

@Composable
fun TransactionItemView(item: TransactionItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        border = BorderStroke(1.dp, Color(0xFFFECD40)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(28.dp),
                painter = item.icon,
                contentDescription = item.title,
                tint = Color(0xFFFECD40)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = item.title, fontWeight = FontWeight.Medium, fontSize = 14.sp)
                Text(text = item.date, fontWeight = FontWeight.Normal, fontSize = 10.sp)
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(text = item.amount, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = if (item.isExpense) Icons.Default.ArrowOutward else Icons.AutoMirrored.Filled.CallReceived,
                contentDescription = if (item.isExpense) "Expense" else "Income",
                tint = if (item.isExpense) Color(0xFFFF2828) else Color(0xFF40FE7F),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun TransactionList(
    transactions: List<TransactionItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(transactions) { item ->
            TransactionItemView(item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionListPreview() {
    TransactionList(
        transactions = getTransactionItems(),
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun TransactionItemPreview() {
    TransactionItemView(item = getTransactionItems()[0])
}