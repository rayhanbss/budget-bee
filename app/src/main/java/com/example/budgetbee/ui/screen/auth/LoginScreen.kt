package com.example.budgetbee.ui.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.budgetbee.ui.theme.Black
import com.example.budgetbee.ui.theme.White

@Composable
fun LoginScreen(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Text(
            text = "Login Screen",
            color = Black,
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = { navController.navigate("dashboard") }) {
            Text("Login")
        }
    }
}