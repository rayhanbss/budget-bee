package com.example.budgetbee.data.model

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Laptop
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.budgetbee.ui.theme.YellowPrimary

data class TargetItem(
    val name: String,
    val amount: String,
    val icon: @Composable () -> Unit,
    val progress: Float,
    val category: String
)

val allTargets = listOf(
    TargetItem(
        "Buy A Car",
        "Rp150.000.000",
        { Icon(Icons.Default.DirectionsCar, null, tint = YellowPrimary, modifier = Modifier.size(40.dp)) },
        0.25f,
        "Kendaraan"
    ),
    TargetItem(
        "Trip to Bali",
        "Rp10.000.000",
        { Icon(Icons.Default.Flight, null, tint = YellowPrimary, modifier = Modifier.size(40.dp)) },
        0.6f,
        "Liburan"
    ),
    TargetItem(
        "New Laptop",
        "Rp20.000.000",
        { Icon(Icons.Default.Laptop, null, tint = YellowPrimary, modifier = Modifier.size(40.dp)) },
        0.45f,
        "Gadget"
    ),
    TargetItem(
        "Buy Motorcycle",
        "Rp25.000.000",
        { Icon(Icons.Default.DirectionsCar, null, tint = YellowPrimary, modifier = Modifier.size(40.dp)) },
        0.1f,
        "Kendaraan"
    )
)

val recommendedTargets = listOf(
    TargetItem(
        name = "Belanja Lebaran",
        amount = "Rp5.000.000",
        icon = {
            Icon(
                imageVector = Icons.Default.Flight,
                contentDescription = null,
                tint = YellowPrimary,
                modifier = Modifier.size(32.dp)
            )
        },
        progress = 0.1f,
        category = "Liburan"
    ),
    TargetItem(
        name = "Upgrade PC",
        amount = "Rp8.000.000",
        icon = {
            Icon(
                imageVector = Icons.Default.Laptop,
                contentDescription = null,
                tint = YellowPrimary,
                modifier = Modifier.size(32.dp)
            )
        },
        progress = 0.2f,
        category = "Gadget"
    ),
    TargetItem(
        name = "Umroh",
        amount = "Rp30.000.000",
        icon = {
            Icon(
                imageVector = Icons.Default.Flight,
                contentDescription = null,
                tint = YellowPrimary,
                modifier = Modifier.size(32.dp)
            )
        },
        progress = 0.35f,
        category = "Liburan"
    ),
    TargetItem(
        name = "Beli Kamera",
        amount = "Rp12.000.000",
        icon = {
            Icon(
                imageVector = Icons.Default.Laptop,
                contentDescription = null,
                tint = YellowPrimary,
                modifier = Modifier.size(32.dp)
            )
        },
        progress = 0.15f,
        category = "Gadget"
    ),
    TargetItem(
        name = "Pernikahan",
        amount = "Rp50.000.000",
        icon = {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = YellowPrimary,
                modifier = Modifier.size(32.dp)
            )
        },
        progress = 0.05f,
        category = "Keluarga"
    ),
    TargetItem(
        name = "Tabungan Rumah",
        amount = "Rp150.000.000",
        icon = {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = null,
                tint = YellowPrimary,
                modifier = Modifier.size(32.dp)
            )
        },
        progress = 0.25f,
        category = "Investasi"
    )
)

