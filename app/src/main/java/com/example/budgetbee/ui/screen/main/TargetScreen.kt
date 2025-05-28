package com.example.budgetbee.ui.screen.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Laptop
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.budgetbee.ui.theme.BudgetBeeTheme
import com.example.budgetbee.ui.theme.YellowPrimary
import com.example.budgetbee.ui.theme.YellowSecondary
import com.example.budgetbee.ui.theme.YellowTertiary

@Composable
fun TargetScreen() {
    val allTargets = listOf(
        Target(
            "Buy A Car",
            "Rp150.000.000",
            { Icon(Icons.Default.DirectionsCar, null, tint = YellowPrimary, modifier = Modifier.size(40.dp)) },
            0.25f,
            "Kendaraan"
        ),
        Target(
            "Trip to Bali",
            "Rp10.000.000",
            { Icon(Icons.Default.Flight, null, tint = YellowPrimary, modifier = Modifier.size(40.dp)) },
            0.6f,
            "Liburan"
        ),
        Target(
            "New Laptop",
            "Rp20.000.000",
            { Icon(Icons.Default.Laptop, null, tint = YellowPrimary, modifier = Modifier.size(40.dp)) },
            0.45f,
            "Gadget"
        ),
        Target(
            "Buy Motorcycle",
            "Rp25.000.000",
            { Icon(Icons.Default.DirectionsCar, null, tint = YellowPrimary, modifier = Modifier.size(40.dp)) },
            0.1f,
            "Kendaraan"
        )
    )

    val recommendedTargets = listOf(
        Target(
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
        Target(
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
        Target(
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
        Target(
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
        Target(
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
        Target(
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

    val categories = listOf("All", "Kendaraan", "Gadget", "Liburan", "Pendidikan")

    var selectedCategory by remember { mutableStateOf("All") }

    val filteredTargets = if (selectedCategory == "All") allTargets
    else allTargets.filter { it.category == selectedCategory }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(YellowTertiary)
            .padding(bottom = 16.dp)
    ) {
        item {
            Text(
                text = "Set Target",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = YellowPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, bottom = 16.dp),
                textAlign = TextAlign.Center
            )
        }

        item {
            Text(
                text = "Category",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = YellowPrimary,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
        }

        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    CategoryChip(
                        name = category,
                        selected = category == selectedCategory,
                        onClick = { selectedCategory = category }
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Text(
                text = "My Target",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = YellowPrimary,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
        }

        items(filteredTargets) { target ->
            CompactTargetCard(
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
                target = target,
                onClick = {
                    // TODO: aksi klik target card
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            Text(
                text = "Recommendation Target",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = YellowPrimary,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
        }

        item {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .height(200.dp)
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(recommendedTargets) { target ->
                    MiniTargetCard(
                        target = target,
                        onClick = {
                            // TODO: aksi klik mini target card
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CompactTargetCard(
    modifier: Modifier = Modifier,
    target: Target,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .heightIn(min = 100.dp, max = 120.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, YellowPrimary)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(40.dp),
                contentAlignment = Alignment.Center
            ) {
                target.icon()
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = target.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = target.amount,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(6.dp))
                LinearProgressIndicator(
                    progress = { target.progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = YellowSecondary,
                    trackColor = YellowTertiary,
                )
            }
        }
    }
}

@Composable
fun MiniTargetCard(target: Target, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, YellowPrimary)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Ikon
            target.icon()
            Spacer(modifier = Modifier.height(8.dp))

            // Nama target
            Text(
                text = target.name,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            // Nominal
            Text(
                text = target.amount,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun CategoryChip(name: String, selected: Boolean, onClick: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = if (selected) YellowPrimary else YellowPrimary.copy(alpha = 0.2f),
        border = BorderStroke(1.dp, YellowPrimary),
        modifier = Modifier.clickable { onClick() }
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            color = if (selected) Color.White else YellowPrimary,
            fontSize = 14.sp
        )
    }
}

data class Target(
    val name: String,
    val amount: String,
    val icon: @Composable () -> Unit,
    val progress: Float,
    val category: String
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TargetScreenPreview() {
    BudgetBeeTheme {
        TargetScreen()
    }
}








