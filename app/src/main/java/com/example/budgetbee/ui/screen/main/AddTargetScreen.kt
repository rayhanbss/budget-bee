package com.example.budgetbee.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import android.app.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.budgetbee.data.model.User
import com.example.budgetbee.ui.theme.Black
import com.example.budgetbee.ui.theme.White
import com.example.budgetbee.ui.theme.YellowPrimary
import com.example.budgetbee.viewmodel.TargetViewModel
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTargetScreen (
    navController: NavController,
    targetViewModel: TargetViewModel,
    user: User?,
    tokenString: String?
) {
    val context = LocalContext.current
    val name = remember { mutableStateOf("") }
    val amountNeeded = remember { mutableStateOf("") }
    val deadline = remember { mutableStateOf("") }
    val dateFormatter = remember { java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()) }
    val timeFormatter = remember { java.text.SimpleDateFormat("HH:mm:ss", java.util.Locale.getDefault()) }
    val calendar = remember { java.util.Calendar.getInstance() }
    val isTargetCreated = targetViewModel.isTargetCreated

    LaunchedEffect(isTargetCreated) {
        if (targetViewModel.isTargetCreated) {
            navController.navigate("target")
            targetViewModel.isTargetCreated = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
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
                onValueChange = {},
                label = { Text("Deadline (YYYY-MM-DD HH-MM-SS)") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange, contentDescription = "Select deadline",
                        tint = YellowPrimary,
                        modifier = Modifier.clickable {
                            DatePickerDialog(
                                context,
                                { _, year, month, dayOfMonth ->
                                    calendar.set(java.util.Calendar.YEAR, year)
                                    calendar.set(java.util.Calendar.MONTH, month)
                                    calendar.set(java.util.Calendar.DAY_OF_MONTH, dayOfMonth)
                                    TimePickerDialog(
                                        context,
                                        { _, hourOfDay, minute ->
                                            calendar.set(java.util.Calendar.HOUR_OF_DAY, hourOfDay)
                                            calendar.set(java.util.Calendar.MINUTE, minute)
                                            calendar.set(java.util.Calendar.SECOND, 0)
                                            val date = dateFormatter.format(calendar.time)
                                            val time = timeFormatter.format(calendar.time)
                                            deadline.value = "$date $time"
                                        },
                                        calendar.get(java.util.Calendar.HOUR_OF_DAY),
                                        calendar.get(java.util.Calendar.MINUTE),
                                        true
                                    ).show()
                                },
                                calendar.get(java.util.Calendar.YEAR),
                                calendar.get(java.util.Calendar.MONTH),
                                calendar.get(java.util.Calendar.DAY_OF_MONTH)
                            ).show()
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
            )
        }

        Button(
            onClick = {
                targetViewModel.createTarget(
                    user = user,
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
                .padding(16.dp)
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

