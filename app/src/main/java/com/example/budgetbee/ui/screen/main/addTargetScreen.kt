package com.example.budgetbee.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.budgetbee.data.model.User
import com.example.budgetbee.ui.theme.Black
import com.example.budgetbee.ui.theme.White
import com.example.budgetbee.ui.theme.YellowPrimary
import com.example.budgetbee.viewmodel.TargetViewModel

@Composable
fun AddTargetScreen (
    navController: NavController,
    targetViewModel: TargetViewModel,
    user: User?,
    tokenString: String?
) {
    val name = remember { mutableStateOf("") }
    val amountNeeded = remember { mutableStateOf("") }
    val deadline = remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val isTargetCreated = targetViewModel.isTargetCreated

    LaunchedEffect (isTargetCreated) {
        if (isTargetCreated) {
            navController.popBackStack()
            targetViewModel.isTargetCreated = false
        }
    }

    Scaffold(
        modifier = Modifier.background(White),
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
                    text = "Target",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = YellowPrimary
                )
            }
            OutlinedTextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Target Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
            )
            OutlinedTextField(
                value = amountNeeded.value,
                onValueChange = { amountNeeded.value = it },
                label = { Text("Amount Needed") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
            )
            OutlinedTextField(
                value = deadline.value,
                onValueChange = { deadline.value = it },
                label = { Text("Deadline (DD-MM-YYYY)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedButton(
                    onClick = {
                        targetViewModel.createTarget(
                            userId = user?.id.toString(),
                            name = name.value,
                            amountNeeded = amountNeeded.value.toDoubleOrNull() ?: 0.0,
                            deadline = deadline.value,
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
                        contentDescription = "Add Target"
                    )
                    Text(
                        text = "Add New Target",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}