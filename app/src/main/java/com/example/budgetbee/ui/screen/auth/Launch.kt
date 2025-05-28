package com.example.budgetbee

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.budgetbee.ui.theme.YellowPrimary

@Composable
fun Launch() {
    Column(
        modifier = Modifier.fillMaxSize().background(YellowPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo2),
            contentDescription = "Logo",
            modifier = Modifier.size(width = 121.dp, height = 135.dp)
        )
        Image(
            painter = painterResource(R.drawable.bubee),
            contentDescription = "Budget Bee",
            modifier = Modifier.height(52.dp).size(128.dp)
        )
    }
}

@Preview
@Composable
fun LaunchPreview() {
    Launch()
}