package com.example.budgetbee.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.budgetbee.R

@Composable
fun ProfileScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        // Top Background + Back Button + Title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(Color(0xFFF5D98A)) // Krem pastel
        ) {
            IconButton(
                onClick = { /* TODO: back navigation */ },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.DarkGray
                )
            }

            Text(
                text = "Profile",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B2B29),
                modifier = Modifier.align(Alignment.TopCenter).padding(top = 20.dp)
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Foto Profil
                Image(
                    painter = painterResource(id = R.drawable.profile_photo), // ganti dgn foto kamu
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "John Smith",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF1B2B29)
                )

                Text(
                    text = "ID: 25030024",
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Menu Items
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            ProfileMenuItem(icon = R.drawable.ic_edit, label = "Edit Profile", bgColor = Color(0xFFF5E6AA))
            ProfileMenuItem(icon = R.drawable.ic_security, label = "Security", bgColor = Color(0xFFFAD23D))
            ProfileMenuItem(icon = R.drawable.ic_setting, label = "Setting", bgColor = Color(0xFFFFB940))
            ProfileMenuItem(icon = R.drawable.ic_help, label = "Help", bgColor = Color(0xFFF5E6AA))
            ProfileMenuItem(icon = R.drawable.ic_logout, label = "Logout", bgColor = Color(0xFFFAD23D))
        }
    }
}

@Composable
fun ProfileMenuItem(icon: Int, label: String, bgColor: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(bgColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1B2B29)
        )
    }
}
