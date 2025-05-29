package com.example.budgetbee.ui.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ForgotPasswordScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFB84C))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(top = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Forgot",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "Password?",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 160.dp)
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(Color(0xFFFFF6DC))
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Enter your email", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                label = { Text("Email") },
                value = email,
                onValueChange = { email = it },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                shape = RoundedCornerShape(30.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black,
                    cursorColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    navController.navigate("login")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("Send Code", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Back to Login",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Blue,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
        }
    }
}
