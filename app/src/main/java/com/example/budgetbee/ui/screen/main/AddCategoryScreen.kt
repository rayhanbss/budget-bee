package com.example.budgetbee.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.budgetbee.data.model.User
import com.example.budgetbee.ui.theme.Black
import com.example.budgetbee.ui.theme.Grey
import com.example.budgetbee.ui.theme.White
import com.example.budgetbee.ui.theme.YellowPrimary
import com.example.budgetbee.viewmodel.CategoryViewModel

@Composable
fun AddCategoryScreen(
    navController: NavHostController,
    categoryViewModel: CategoryViewModel,
    user: User?,
    tokenString: String? = null
) {
    // Redirect to login if no token or user
    LaunchedEffect(tokenString, user) {
        if (tokenString.isNullOrBlank() || user?.id == null) {
            navController.navigate("login") {
                popUpTo("login") { inclusive = true }
            }
        }
    }
    val name = remember { mutableStateOf("") }
    val isExpense = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.background(White)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .background(White)
        ) {
            Row {
                Text(
                    text = "Add ",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Black
                )
                Text(
                    text = "Category",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = YellowPrimary
                )
            }
            OutlinedTextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Category Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Is Expenses?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Black
                )
                Switch(
                    checked = isExpense.value,
                    onCheckedChange = {
                        isExpense.value = it
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = YellowPrimary,
                        checkedBorderColor = YellowPrimary,
                        checkedTrackColor = White,
                        uncheckedTrackColor = White,
                        uncheckedThumbColor = Grey
                    ),
                )
            }
            Button(
                onClick = {
                    categoryViewModel.createCategory(
                        user = user,
                        name = name.value,
                        isExpense = isExpense.value,
                        token = tokenString
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = YellowPrimary,
                    contentColor = White
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Category",
                )
                Text(
                    text = "Add New Category",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        // Navigate when category is created
        val isCreated = categoryViewModel.isCategoryCreated
        LaunchedEffect(isCreated) {
            if (isCreated) {
                navController.navigate("dashboard") {
                    popUpTo("dashboard") { inclusive = true }
                }
            }
        }
    }
}
