package com.example.budgetbee.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.budgetbee.data.model.Target
import com.example.budgetbee.data.model.User
import com.example.budgetbee.ui.theme.Black
import com.example.budgetbee.ui.theme.Failed
import com.example.budgetbee.ui.theme.White
import com.example.budgetbee.ui.theme.YellowPrimary
import com.example.budgetbee.ui.theme.YellowTertiary
import com.example.budgetbee.util.CurrencyUtils
import com.example.budgetbee.viewmodel.TargetViewModel
import android.util.Log
import androidx.compose.runtime.MutableState

@Composable
fun TargetDetail(
    target: Target,
    progress: Float,
    targetViewModel: TargetViewModel,
    user: User?,
    token: String,
    navController: NavHostController,
    showBottomSheet: MutableState<Boolean>,

    ) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row{
                Text(
                    text = "Target ",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Black
                )
                Text(
                    text = "Detail",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = YellowPrimary
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        navController.navigate("edit_target/${target.id}")
                        showBottomSheet.value = false
                    },
                    modifier = Modifier
                        .size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Target",
                        tint = YellowPrimary
                    )
                }
                IconButton(
                    onClick = { showDeleteDialog = true },
                    modifier = Modifier
                        .size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Target",
                        tint = Failed
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(2.dp, shape = RoundedCornerShape(8.dp))
                    .background(White),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 16.dp, 16.dp, 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = target.name,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                    )
                    Text(
                        text = target.status,
                        fontSize = 14.sp,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 8.dp, 16.dp, 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Deadline",
                        fontSize = 14.sp,
                    )
                    Text(
                        text = target.deadline.substringBefore(" "),
                        fontSize = 14.sp,
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(2.dp, shape = RoundedCornerShape(8.dp))
                    .background(White),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 16.dp, 16.dp, 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Progress",
                        fontSize = 14.sp,
                    )
                    Text(
                        text =
                            CurrencyUtils.format(target.amountCollected) +
                                    " / ${CurrencyUtils.format(target.amountNeeded)}",
                        fontSize = 14.sp,
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 8.dp, 16.dp, 16.dp)
                        .height(8.dp),
                    color = YellowPrimary,
                    trackColor = YellowTertiary,
                    strokeCap = StrokeCap.Round,
                )
            }
        }
        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                shape = RoundedCornerShape(8.dp),
                containerColor = White,
                titleContentColor = YellowPrimary,
                textContentColor = Black,
                title = { Text(text = "Confirm Delete", fontWeight = FontWeight.Bold) },
                text = { Text(text = "Are you sure you want to delete this transaction?") },
                confirmButton = {
                    TextButton(onClick = {
                        Log.i("TargetDetail", "Deleting target with ID: ${target.id}, ${user?.id} $token")
                        targetViewModel.deleteTarget(user, target.id, token)
                    }) {
                        Text("Delete", color = Failed)
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showDeleteDialog = false },
                    ) {
                        Text("Cancel", color = Black)
                    }
                }
            )
        }
    }
}