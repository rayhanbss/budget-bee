package com.example.budgetbee.ui.component
import com.example.budgetbee.ui.theme.*
import com.example.budgetbee.R.drawable.*

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun NavBar(
    navController: NavController,
    currentRoute: String
    ){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                .background(White)
                .padding(24.dp, 0.dp, 24.dp, 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.navigate("dashboard")  },
                modifier = Modifier
                    .size(50.dp)
                    .padding(5.dp)
            ) {
                Icon(
                    if (currentRoute == "dashboard") Icons.Filled.Analytics else Icons.Outlined.Analytics,
                    contentDescription = "Dashboard",
                    tint = YellowPrimary,
                    modifier = Modifier.size(30.dp)
                )
            }
            IconButton(
                onClick = { navController.navigate("transaction") },
                modifier = Modifier
                    .size(50.dp)
                    .padding(5.dp)
            ) {
                Icon(
                    if (currentRoute == "transaction") Icons.Filled.AccountBalanceWallet else Icons.Outlined.AccountBalanceWallet,
                    contentDescription = "Transactions",
                    tint = YellowPrimary,
                    modifier = Modifier.size(30.dp)
                )
            }
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.size(50.dp)
            ) {
                Icon(
                    painter = painterResource(addtransaction),
                    contentDescription = "Add Transaction",
                    modifier = Modifier.size(60.dp),
                    tint = YellowPrimary
                )
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Transaction",
                    modifier = Modifier.size(40.dp),
                    tint = YellowTertiary
                )
            }
            IconButton(
                onClick = { navController.navigate("target")  },
                modifier = Modifier
                    .size(50.dp)
                    .padding(5.dp)
            ) {
                Icon(
                    if (currentRoute == "target") Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                    contentDescription = "Target",
                    tint = YellowPrimary,
                    modifier = Modifier.size(30.dp)
                )
            }
            IconButton(
                onClick = { navController.navigate("profile")  },
                modifier = Modifier
                    .size(50.dp)
                    .padding(5.dp)
            ) {
                Icon(
                    if (currentRoute == "profile") Icons.Filled.AccountBox else Icons.Outlined.AccountBox,
                    contentDescription = "Profile",
                    tint = YellowPrimary,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun NavBarPreview() {
    NavBar(
        navController = NavController(LocalContext.current),
        currentRoute = "dashboard"
    )
}