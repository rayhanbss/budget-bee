package com.example.budgetbee.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.budgetbee.ui.theme.Black
import com.example.budgetbee.ui.theme.White

@Composable
fun TargetScreen(modifier: Modifier = Modifier){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Text(
            text = "Target Screen",
            color = Black,
            modifier = Modifier.padding(16.dp)
        )
    }
}