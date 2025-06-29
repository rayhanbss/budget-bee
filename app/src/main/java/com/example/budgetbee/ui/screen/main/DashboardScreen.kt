package com.example.budgetbee.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.budgetbee.ui.component.CompactTargetCard
import com.example.budgetbee.ui.component.TransactionRow
import com.example.budgetbee.ui.theme.Black
import com.example.budgetbee.ui.theme.Failed
import com.example.budgetbee.ui.theme.Success
import com.example.budgetbee.ui.theme.White
import com.example.budgetbee.ui.theme.YellowPrimary
import com.example.budgetbee.viewmodel.TransactionViewModel
import com.example.budgetbee.viewmodel.UserViewModel

@Composable
fun DashboardScreen(
    userViewModel: UserViewModel,
    transactionViewModel: TransactionViewModel,
    tokenString: String? = null,
    navController: NavHostController
) {
    val userState = userViewModel.userState.collectAsState()
    val user = userState.value
    val accountBalance = 999999.00
    val income = 1000000.00
    val expense = 100000.00

    LaunchedEffect(user, tokenString) {
        transactionViewModel.getAllTransactions(user, tokenString)
    }
    val transactionList = transactionViewModel.transactions
    val filteredTransactions = transactionList.sortedByDescending { it.dateTransaction }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
    ){
        item{
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ){
                // Dashboard top
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                            .shadow((4.dp), shape = RoundedCornerShape(8.dp))
                            .background(White),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Hello, ",
                                color = Black,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = "${user?.name ?: "User"}!",
                                color = YellowPrimary,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                            )

                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp)
                                .shadow(
                                    elevation = (4.dp),
                                    shape = RoundedCornerShape(8.dp))
                                .background(YellowPrimary),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center
                        ){
                            Text(
                                text = "Account Balance",
                                color = Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                                    .padding(18.dp, 12.dp, 18.dp, 0.dp)
                            )
                            Text(
                                text = "Rp. $accountBalance",
                                color = White,
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(18.dp, 0.dp, 18.dp, 12.dp)

                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp, 0.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Row(
                                modifier = Modifier
                                    .height(64.dp)
                                    .weight(1f)
                                    .shadow((4.dp), shape = RoundedCornerShape(8.dp))
                                    .background(White),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Icon(
                                    imageVector = Icons.Default.ArrowOutward,
                                    contentDescription = "Income Icon",
                                    tint = YellowPrimary,
                                    modifier = Modifier
                                        .rotate(180f)
                                        .size(48.dp)
                                )
                                Column(
                                    modifier = Modifier
                                        .padding(4.dp)
                                ){
                                    Text(
                                        text = "Income",
                                        color = Black,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "Rp. $income",
                                        color = Black,
                                        fontSize = 14.sp,
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .height(64.dp)
                                    .weight(1f)
                                    .shadow((4.dp), shape = RoundedCornerShape(8.dp))
                                    .background(White),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Icon(
                                    imageVector = Icons.Default.ArrowOutward,
                                    contentDescription = "Expenses Icon",
                                    tint = YellowPrimary,
                                    modifier = Modifier
                                        .size(48.dp)
                                )
                                Column(
                                    modifier = Modifier
                                        .padding(4.dp)
                                ){
                                    Text(
                                        text = "Expenses",
                                        color = Black,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "Rp. $income",
                                        color = Black,
                                        fontSize = 14.sp,
                                    )
                                }
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, bottom = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = "Profit",
                                color = Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Rp. ${income - expense}",
                                color = if (income - expense >= 0) Success else Failed,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }

        item{Spacer(modifier = Modifier.height(12.dp))}

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 8.dp)
                    .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                    .background(White)
                    .padding(bottom = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.BookmarkBorder,
                        contentDescription = "Goal Icon",
                        tint = YellowPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "My ",
                        color = Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Targets",
                        color = YellowPrimary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                // Use a Column instead of LazyColumn for previews and better Compose preview support
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
//                    Target.take(3).forEach { item ->
//                        CompactTargetCard(
//                            target = item,
//                            modifier = Modifier
//                                .padding(horizontal = 16.dp, vertical = 4.dp)
//                                .fillMaxWidth(),
//                            onClick = { }
//                        )
//                    }
                }
            }
        }

        item{Spacer(modifier = Modifier.height(12.dp))}

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 8.dp)
                    .shadow(2.dp, shape = RoundedCornerShape(8.dp))
                    .background(White)
                    .padding(bottom = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AccountBalanceWallet,
                        contentDescription = "Transaction Icon",
                        tint = YellowPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Recent ",
                        color = Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Transactions",
                        color = YellowPrimary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    filteredTransactions.take(3).forEach { item ->
                        TransactionRow(
                            item = item,
                            navController = navController, // Replace with actual navController if available
                            user = user,
                            tokenString = tokenString ?: "",
                            transactionViewModel = transactionViewModel,
                        )
                    }
                }
            }
        }

    }
}

//@Preview
//@Composable
//fun DashboardScreenPreview() {
//    DashboardScreen()
//}

