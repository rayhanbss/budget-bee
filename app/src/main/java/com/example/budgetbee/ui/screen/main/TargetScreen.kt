package com.example.budgetbee.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.budgetbee.R
import com.example.budgetbee.data.model.TargetItem
import com.example.budgetbee.data.model.allTargets
import com.example.budgetbee.data.model.recommendedTargets
import com.example.budgetbee.ui.component.CategoryChip
import com.example.budgetbee.ui.component.CompactTargetCard
import com.example.budgetbee.ui.component.MiniTargetCard
import com.example.budgetbee.ui.theme.BudgetBeeTheme
import com.example.budgetbee.ui.theme.White
import com.example.budgetbee.ui.theme.YellowPrimary

@Composable
fun TargetScreen() {
    val categories = listOf("All", "Kendaraan", "Gadget", "Liburan", "Pendidikan")

    var selectedCategory by remember { mutableStateOf("All") }

    val filteredTargets = if (selectedCategory == "All") allTargets
    else allTargets.filter { it.category == selectedCategory }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(bottom = 16.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.header),
                    contentDescription = "Header",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Set Target",
                    textAlign = TextAlign.Center,
                    color = YellowPrimary,
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }
        }

        item {
            Text(
                text = "Category",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = YellowPrimary,
                modifier = Modifier.padding(32.dp, 4.dp)
            )
        }

        item {
            LazyRow(
                contentPadding = PaddingValues(32.dp, 4.dp),
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
                modifier = Modifier.padding(32.dp, 4.dp)
            )
        }

        items(filteredTargets) { target ->
            CompactTargetCard(
                modifier = Modifier.padding(32.dp, 4.dp),
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
                modifier = Modifier.padding(32.dp, 4.dp)
            )
        }

        item {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .heightIn(min = 200.dp, max = 500.dp)
                    .padding(horizontal = 32.dp),
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TargetScreenPreview() {
    BudgetBeeTheme {
        TargetScreen()
    }
}








