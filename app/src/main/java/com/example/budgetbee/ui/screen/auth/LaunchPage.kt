package com.example.budgetbee.ui.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.budgetbee.R
import com.example.budgetbee.ui.theme.YellowPrimary
import com.example.budgetbee.ui.theme.YellowSecondary

@Composable
fun LaunchPage(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(width = 121.dp, height = 135.dp)
        )
        Image(
            painter = painterResource(R.drawable.budgetbee),
            contentDescription = "Budget Bee",
            modifier = Modifier.height(52.dp).size(128.dp)
        )
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod.",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(236.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {navController.navigate("login")},
            modifier = Modifier
                .padding(5.dp)
                .width(200.dp)
                .height(45.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = YellowPrimary, contentColor = Color.Black)
        ) {
            Text("Log In", fontSize = 20.sp)
        }
        Button(
            onClick = { navController.navigate("register") },
            modifier = Modifier
                .padding(5.dp)
                .width(200.dp)
                .height(45.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = YellowSecondary, contentColor = Color.Black)
        ) {
            Text("Sign Up", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Forgot Password?",
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(5.dp)
                .clickable { navController.navigate("forgot") }
        )
    }
}

@Preview
@Composable
fun LaunchPagePreview () {
}