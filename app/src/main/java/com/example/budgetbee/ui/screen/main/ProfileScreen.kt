package com.example.budgetbee.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.budgetbee.R
import com.example.budgetbee.ui.theme.White
import com.example.budgetbee.ui.theme.YellowPrimary
import com.example.budgetbee.ui.theme.YellowTertiary

@Composable
fun ProfileScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background krem pastel paling bawah
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(YellowPrimary)
        ) {

            Text(
                text = "Profile",
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                color = White,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 48.dp)
            )
        }

        // Container putih melengkung di bawah background krem
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
                .offset(y = 180.dp)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(Color.White)
                .padding(top = 160.dp, start = 32.dp, end = 32.dp)
        ) {
            // Menu item profile
            ProfileMenuItem(icon = R.drawable.ic_edit, label = "Edit Profile", bgColor = Color(0xFFF5E6AA))
            ProfileMenuItem(icon = R.drawable.ic_security, label = "Security", bgColor = Color(0xFFFAD23D))
            ProfileMenuItem(icon = R.drawable.ic_setting, label = "Setting", bgColor = Color(0xFFFFB940))
            ProfileMenuItem(icon = R.drawable.ic_help, label = "Help", bgColor = Color(0xFFF5E6AA))
            ProfileMenuItem(
                icon = R.drawable.ic_logout,
                label = "Logout",
                bgColor = Color(0xFFFAD23D),
                onClick = {navController.navigate("launch")
            })
        }

        // Foto profil di layer paling atas supaya overlap rapi dengan background
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 130.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_photo),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
                    .border(3.dp, Color.White, CircleShape)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "John Smith",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color(0xFF1B2B29)
            )

            Text(
                text = "ID: 25030024",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}


@Composable
fun ProfileMenuItem(icon: Int, label: String, bgColor: Color, onClick: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(50.dp) // sedikit lebih besar
                .background(bgColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = label,
                modifier = Modifier.size(50.dp)
                )
        }

        Spacer(modifier = Modifier.width(20.dp)) // jarak label lebih jauh dari ikon

        Text(
            text = label,
            fontSize = 18.sp, // font lebih besar
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1B2B29)
        )
    }
}







