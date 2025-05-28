package com.example.budgetbee.ui.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.budgetbee.ui.theme.Black
import com.example.budgetbee.ui.theme.White

@Composable
fun LoginScreen(navController: NavController){
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
                text = "Welcome To",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "Budget Bee!",
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
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginFormFields(navController)
        }
    }
}

@Composable
fun LoginFormFields(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    // --- Email Field ---
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        OutlinedTextField(
            label = { Text("Username", color = Black) },
            value = email,
            onValueChange = { email = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            shape = RoundedCornerShape(30.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Black,
                unfocusedBorderColor = Gray,
                focusedLabelColor = Black,
                unfocusedLabelColor = Gray
            )
        )
    }

    // --- Password Field ---
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        OutlinedTextField(
            value = password,
            label = { Text("Password", color = Black) },
            onValueChange = { password = it },
            singleLine = true,
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        imageVector = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = "Toggle Password"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            shape = RoundedCornerShape(30.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Black,
                unfocusedBorderColor = Gray,
                focusedLabelColor = Black,
                unfocusedLabelColor = Gray
            )
        )
    }

    // --- Login Button ---
    Button(
        onClick = { navController.navigate("dashboard") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .height(50.dp),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
    ) {
        Text("Log In", color = White, fontWeight = FontWeight.Bold)
    }

    // --- Forgot Password Text ---
    Text(
        text = "Forgot Password?",
        modifier = Modifier
            .padding(top = 12.dp)
            .clickable { /* Handle Forgot Password */ },
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        color = Black
    )

    Spacer(modifier = Modifier.height(36.dp))

    // --- Sign Up Text ---
    Row {
        Text(text = "Don't have an account?", fontSize = 12.sp, color = Black)
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "Sign Up",
            fontSize = 12.sp,
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { /* Handle Sign Up */ }
        )
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(navController = NavController(context = LocalContext.current))
}