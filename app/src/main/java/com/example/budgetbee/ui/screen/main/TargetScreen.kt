package com.example.budgetbee.ui.screen.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Laptop
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.budgetbee.R
import com.example.budgetbee.data.model.Target
import com.example.budgetbee.data.model.User
import com.example.budgetbee.ui.component.CategoryChip
import com.example.budgetbee.ui.component.CompactTargetCard
import com.example.budgetbee.ui.component.MiniTargetCard
import com.example.budgetbee.ui.theme.Black
import com.example.budgetbee.ui.theme.BudgetBeeTheme
import com.example.budgetbee.ui.theme.White
import com.example.budgetbee.ui.theme.YellowPrimary
import com.example.budgetbee.viewmodel.TargetViewModel
import com.example.budgetbee.viewmodel.TransactionViewModel
import com.google.accompanist.placeholder.placeholder

@Composable
fun TargetScreen(
    targetViewModel: TargetViewModel,
    user: User?,
    token: String?
) {

    LaunchedEffect(user, token) {
        if (user != null && token != null) {
            Log.i("TargetScreen", "Calling getAllTarget with user: ${user.id}, token available: ${token.isNotBlank()}")
            targetViewModel.getAllTarget(user, token)
        } else {
            Log.w("TargetScreen", "User or token is null - user: $user, token: $token")
        }
    }

    val isLoading = targetViewModel.isLoading
    val targetList = targetViewModel.targets
    val errorMessage = targetViewModel.errorMessage

    Log.i("TargetScreen", "Target list size: ${targetList.size}")
    Log.i("TargetScreen", "Is loading: $isLoading")
    Log.i("TargetScreen", "Error message: $errorMessage")

    var searchQuery by remember { mutableStateOf("") }

    val filteredTargets = targetList
        .filter { item ->
            val matchesQuery = searchQuery.isBlank() || item.name.contains(searchQuery, ignoreCase = true)
            matchesQuery
        }
        .sortedByDescending { it.deadline }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(2.dp, shape = RoundedCornerShape(12.dp))
                .background(White),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 16.dp, 16.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "My ",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Black
                )
                Text(
                    text = "Target",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = YellowPrimary
                )
            }
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search Target") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 8.dp, 16.dp, 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Black,
                    focusedLabelColor = Black,
                    unfocusedLabelColor = Gray,
                    focusedTextColor = Black,
                    unfocusedTextColor = Black,
                ),
                singleLine = true,
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear",
                            tint = Black,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .clickable { searchQuery = "" }
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Black
                        )
                    }
                }
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if (isLoading) {
                items(10) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .shadow(2.dp, shape = RoundedCornerShape(8.dp))
                            .placeholder(visible = true, color = White, shape = RoundedCornerShape(8.dp))
                    )
                }
            } else if (errorMessage != null) {
                item {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            } else if (filteredTargets.isEmpty()) {
                item {
                    Text(
                        text = "No targets found",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            } else {
                items(filteredTargets) { item ->
                    CompactTargetCard(
                        target = item,
                    )
                }
            }
        }
    }
}


