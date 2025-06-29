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
import com.example.budgetbee.viewmodel.TargetViewModel
import com.example.budgetbee.viewmodel.TransactionViewModel
import com.example.budgetbee.viewmodel.UserViewModel
import com.google.accompanist.placeholder.placeholder
import com.example.budgetbee.util.CurrencyUtils

@Composable
fun DashboardScreen(
    userViewModel: UserViewModel,
    transactionViewModel: TransactionViewModel,
    targetViewModel: TargetViewModel,
    tokenString: String? = null,
    navController: NavHostController
) {
    val userState = userViewModel.userState.collectAsState()
    val user = userState.value
    val income = transactionViewModel.income
    val expense = transactionViewModel.expense
    val profit = income - expense
    val isMinus = profit < 0

    val transactionIsLoading = transactionViewModel.isLoading
    val targetIsLoading = targetViewModel.isLoading

    LaunchedEffect(user, tokenString) {
        transactionViewModel.getIncome(user, tokenString)
        transactionViewModel.getExpense(user, tokenString)
        targetViewModel.getAllTarget(user, tokenString)
        transactionViewModel.getAllTransactions(user, tokenString)
    }

    val transactionList = transactionViewModel.transactions
    val filteredTransactions = transactionList.sortedByDescending { it.dateTransaction }

    val targetList = targetViewModel.targets
    val filteredTargets = targetList
        .filter { (it.status == "On Progress") }
        .sortedByDescending { (it.deadline) }

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
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .background(YellowPrimary),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center
                        ){
                            Text(
                                text = "This Month Profit",
                                color = Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                                    .padding(18.dp, 12.dp, 18.dp, 0.dp)
                            )
                            Text(
                                text = CurrencyUtils.format(profit.toDouble()),
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
                                .padding(16.dp, 0.dp, 16.dp, 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .shadow((4.dp), shape = RoundedCornerShape(8.dp))
                                    .background(White),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Icon(
                                    imageVector = Icons.Default.ArrowOutward,
                                    contentDescription = "Income Icon",
                                    tint = Success,
                                    modifier = Modifier
                                        .rotate(180f)
                                        .size(56.dp)
                                        .padding(4.dp, 0.dp)
                                )
                                Column(
                                    modifier = Modifier
                                        .padding(4.dp)
                                ){
                                    Text(
                                        text = "Income",
                                        color = Black,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = if (transactionIsLoading) "" else CurrencyUtils.format(income.toDouble()),
                                        color = Black,
                                        fontSize = 12.sp,
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .shadow((4.dp), shape = RoundedCornerShape(8.dp))
                                    .background(White),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Icon(
                                    imageVector = Icons.Default.ArrowOutward,
                                    contentDescription = "Expenses Icon",
                                    tint = Failed,
                                    modifier = Modifier
                                        .size(56.dp)
                                        .padding(4.dp, 0.dp)
                                )
                                Column(
                                    modifier = Modifier
                                        .padding(4.dp)
                                ){
                                    Text(
                                        text = "Expenses",
                                        color = Black,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = if (transactionIsLoading) "" else CurrencyUtils.format(expense.toDouble()),
                                        color = Black,
                                        fontSize = 12.sp,
                                    )
                                }
                            }
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (targetIsLoading) {
                        repeat(3) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(64.dp)
                                    .shadow(2.dp, shape = RoundedCornerShape(8.dp))
                                    .placeholder(visible = true, color = White, shape = RoundedCornerShape(8.dp))
                            )
                        }
                    } else {
                        filteredTargets.take(3).forEach { item ->
                            CompactTargetCard(
                                target = item,
                                targetViewModel = targetViewModel,
                                user = user,
                                token = tokenString,
                                navController = navController,
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
                    if (transactionIsLoading) {
                        repeat(3) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(64.dp)
                                    .shadow(2.dp, shape = RoundedCornerShape(8.dp))
                                    .placeholder(visible = true, color = White, shape = RoundedCornerShape(8.dp))
                            )
                        }
                    } else {
                        filteredTransactions.take(3).forEach { item ->
                            TransactionRow(
                                item = item,
                                navController = navController,
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
}

//@Preview
//@Composable
//fun DashboardScreenPreview() {
//    DashboardScreen()
//}

