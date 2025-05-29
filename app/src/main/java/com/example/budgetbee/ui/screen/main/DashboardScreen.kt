package com.example.budgetbee.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.budgetbee.R
import com.example.budgetbee.data.model.allTargets
import com.example.budgetbee.data.model.transactions
import com.example.budgetbee.ui.component.CompactTargetCard
import com.example.budgetbee.ui.component.TransactionRow
import com.example.budgetbee.ui.theme.Black
import com.example.budgetbee.ui.theme.Failed
import com.example.budgetbee.ui.theme.Success
import com.example.budgetbee.ui.theme.White
import com.example.budgetbee.ui.theme.YellowPrimary
import com.example.budgetbee.ui.theme.YellowTertiary

@Composable
fun DashboardScreen(modifier: Modifier = Modifier){
    val username = "Username"
    val accountBalance = 999999.00
    val income = 1000000.00
    val expense = 100000.00

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
                    .drawBehind {
                        drawRoundRect(
                            color = YellowTertiary,
                            topLeft = Offset(0f, 0f),
                            size = Size(size.width, 600f),
                        )
                    }
                ) {
                    Image(
                        painter = painterResource(R.drawable.header),
                        contentDescription = "Header",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 200.dp),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ){
                        Text(
                            text = "Hello, $username!",
                            color = YellowPrimary,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(YellowPrimary),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center
                        ){
                            Text(
                                text = "Account Balance",
                                color = White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(16.dp, 12.dp, 16.dp, 4.dp)
                            )
                            Text(
                                text = "Rp. $accountBalance",
                                color = White,
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(16.dp, 4.dp, 16.dp, 12.dp)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Row(
                                modifier = Modifier
                                    .height(64.dp)
                                    .weight(1f)
                                    .border(
                                        width = 2.dp,
                                        color = YellowPrimary,
                                        shape = RoundedCornerShape(12.dp)
                                    ),
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
                                    .border(
                                        width = 2.dp,
                                        color = YellowPrimary,
                                        shape = RoundedCornerShape(12.dp)
                                    ),
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
                                .padding(4.dp, 0.dp),
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

        // Goal
        item{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp, 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Default.BookmarkBorder,
                    contentDescription = "Goal Icon",
                    tint = YellowPrimary,
                    modifier = Modifier
                        .size(24.dp)
                )
                Text(
                    text = "Goal",
                    color = YellowPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        items(allTargets.take(3)) { item ->
            CompactTargetCard(
                target = item,
                modifier = Modifier
                    .padding(32.dp, 4.dp)
                    .fillMaxWidth(),
                onClick = { TODO() }
            )
        }

        // Recent Transaction
        item{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp, 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Outlined.AccountBalanceWallet,
                    contentDescription = "Transaction Icon",
                    tint = YellowPrimary,
                    modifier = Modifier
                        .size(24.dp)
                )
                Text(
                    text = "Recent Transaction",
                    color = YellowPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        items(transactions.take(5)) { item ->
            TransactionRow(
                item = item
            )
        }
    }
}

@Preview
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}