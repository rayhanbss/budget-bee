package com.example.budgetbee.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.budgetbee.R.drawable.addtransaction
import com.example.budgetbee.ui.theme.YellowPrimary
import com.example.budgetbee.ui.theme.YellowTertiary

@Composable
fun BottomNavBar(currentRoute: String, onFabClick: () -> Unit, onNavigate: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(color = Color(0xFFFFF8E1), shape = MaterialTheme.shapes.medium),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onNavigate("dashboard") }) {
                Icon(Icons.Default.BarChart, contentDescription = "Dashboard", tint = YellowPrimary)
            }
            IconButton(onClick = { onNavigate("transaction") }) {
                Icon(Icons.Default.AccountBalanceWallet, contentDescription = "Transaction", tint = YellowPrimary)
            }
            Spacer(modifier = Modifier.width(48.dp))
            IconButton(onClick = { onNavigate("target") }) {
                    Icon(Icons.Default.Bookmark, contentDescription = "Target", tint = YellowPrimary)
            }
            IconButton(onClick = { onNavigate("profile") }) {
                Icon(Icons.Default.Person, contentDescription = "Profile", tint = YellowPrimary)
            }
        }

        FloatingActionButton(
            onClick = onFabClick,
            containerColor = YellowPrimary,
//            shape = MaterialTheme.shapes.medium, // Atau GenericShape custom hexagon
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-28).dp)
                .size(56.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add Transaction",
                modifier = Modifier.size(40.dp),
                tint = YellowTertiary
            )
//            Icon(
//                painter = painterResource(addtransaction),
//                contentDescription = "Add Transaction",
//                modifier = Modifier.size(28.dp),
//                tint = Color.White
//            )
        }
    }
}

@Composable
fun BottomSheetContent(onDismiss: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
//        Text(text = "Choose action", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onDismiss, modifier = Modifier.fillMaxWidth()) {
            Text("Transaction")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onDismiss, modifier = Modifier.fillMaxWidth()) {
            Text("Target")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onDismiss, modifier = Modifier.fillMaxWidth()) {
            Text("Category")
        }
    }
}

@Preview
@Composable
fun BottomNavBarPreview() {
    BottomNavBar (
        currentRoute = "dashboard",
        onFabClick = {},
        onNavigate = {}
    )
}
