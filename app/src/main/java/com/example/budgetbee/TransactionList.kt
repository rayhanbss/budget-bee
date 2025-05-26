package com.example.budgetbee

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
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CallReceived
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.LunchDining
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class TransactionItem (
    val title: String,
    val date: String,
    val amount: String,
    val isExpense: Boolean,
    val icon: ImageVector
)

val transactions = listOf(
    TransactionItem("Online Class", "19/05/2025", "Rp 999.99,-", true, Icons.Default.School),
    TransactionItem("Invesment", "19/05/2025", "Rp 999.99,-", false, Icons.Default.BarChart),
    TransactionItem("Salary", "19/05/2025", "Rp 999.99,-", true, Icons.Default.AttachMoney),
    TransactionItem("Skincare", "19/05/2025", "Rp 999.99,-", true, Icons.Default.HealthAndSafety),
    TransactionItem("Lunch", "19/05/2025", "Rp 999.99,-", true, Icons.Default.LunchDining),
    TransactionItem("House Rent", "19/05/2025", "Rp 999.99,-", false, Icons.Default.House),
    TransactionItem("Shopping", "19/05/2025", "Rp 999.99,-", true, Icons.Default.ShoppingBag),
    TransactionItem("Bus Ticket", "19/05/2025", "Rp 999.99,-", true, Icons.Default.DirectionsBus)
)

@Composable
fun TransactionItemView(item: TransactionItem) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.dp, Color(0xFFFECD40)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = item.icon,
                contentDescription = item.title,
                tint = Color(0xFFFECD40)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = item.title, fontWeight = FontWeight.Normal, fontSize = 12.sp)
                Text(text = item.date, fontWeight = FontWeight.Normal, fontSize = 8.sp)
            }

            Text(text = item.amount, fontWeight = FontWeight.Medium, fontSize = 12.sp)

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
fun TransactionScreen(transactions: List<TransactionItem>) {
    LazyColumn {
        items(transactions) { item ->
            TransactionItemView(item)
        }
    }
}

@Preview
@Composable
fun TransactionScreenPreview() {
    TransactionScreen(transactions)
}