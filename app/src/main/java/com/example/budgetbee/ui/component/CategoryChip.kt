package com.example.budgetbee.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.budgetbee.ui.theme.YellowPrimary
import com.example.budgetbee.ui.theme.YellowTertiary

@Composable
fun CategoryChip(name: String, selected: Boolean, onClick: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = if (selected) YellowPrimary else YellowTertiary,
        border = BorderStroke(1.dp, YellowPrimary),
        modifier = Modifier.clickable { onClick() }
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            color = if (selected) Color.White else YellowPrimary,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    }
}