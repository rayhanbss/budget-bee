package com.example.budgetbee.ui.screen.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.budgetbee.R
import com.example.budgetbee.data.repository.AuthRepository
import com.example.budgetbee.data.repository.TokenRepository
import com.example.budgetbee.data.repository.UserRepository
import com.example.budgetbee.ui.theme.Black
import com.example.budgetbee.ui.theme.White
import com.example.budgetbee.ui.theme.YellowPrimary
import com.example.budgetbee.viewmodel.AuthViewModel
import com.example.budgetbee.viewmodel.AuthViewModelFactory
import com.example.budgetbee.viewmodel.UserViewModel
import com.example.budgetbee.viewmodel.UserViewModelFactory
import androidx.core.net.toUri
import com.example.budgetbee.data.repository.CategoryRepository
import com.example.budgetbee.ui.theme.YellowTertiary

@Composable
fun ProfileScreen(
    context: Context,
    navController: NavController,
    authViewModel: AuthViewModel,
    userViewModel: UserViewModel
) {
    val userState= userViewModel.userState.collectAsState()
    val user = userState.value

    val logoutSuccess = authViewModel.logoutSuccess.collectAsState()

    LaunchedEffect(logoutSuccess.value) {
        if (logoutSuccess.value) {
            navController.navigate("launch")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(2.dp, shape = RoundedCornerShape(12.dp))
                .background(White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "User",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Black
                )
                Text(
                    text = " Profile",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = YellowPrimary
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(96.dp)
                        .clip(CircleShape)
                        .background(YellowTertiary)
                        .border(2.dp, YellowPrimary, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = user?.name?.firstOrNull()?.uppercase() ?: "U",
                        color = YellowPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 40.sp
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = user?.name ?: "User",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color(0xFF1B2B29)
                )

                Text(
                    text = user?.email ?: "Email Placeholder",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(2.dp, shape = RoundedCornerShape(12.dp))
                .background(White)
        ) {
            Button(
                onClick = {
                    val phoneNumber = "6288232815196"
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        setPackage("com.whatsapp")
                        putExtra(Intent.EXTRA_TEXT, "")
                        putExtra("jid", "$phoneNumber@s.whatsapp.net")
                    }
                    context.startActivity(intent)},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 16.dp, 16.dp, 4.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = YellowPrimary,
                    contentColor = White
                ),
            ) {
                Icon(
                    imageVector = Icons.Default.SupportAgent,
                    contentDescription = "Customer Service",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Customer Service",
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                onClick = {
                    authViewModel.logout()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 4.dp, 16.dp, 16.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, YellowPrimary),
                colors = ButtonDefaults.buttonColors(
                    containerColor = White,
                    contentColor = YellowPrimary
                ),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Logout,
                    contentDescription = "Log Out",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Log Out",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun ProfileScreenPreview() {
//    ProfileScreen(navController = NavController(LocalContext.current))
//}

