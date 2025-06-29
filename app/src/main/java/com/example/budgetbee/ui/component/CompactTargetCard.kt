package com.example.budgetbee.ui.component

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.budgetbee.data.model.Target
import com.example.budgetbee.data.model.User
import com.example.budgetbee.ui.theme.Black
import com.example.budgetbee.ui.theme.White
import com.example.budgetbee.ui.theme.YellowPrimary
import com.example.budgetbee.ui.theme.YellowTertiary
import com.example.budgetbee.util.CurrencyUtils
import com.example.budgetbee.viewmodel.TargetViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompactTargetCard(
    modifier: Modifier = Modifier,
    target: Target,
    targetViewModel: TargetViewModel,
    user: User?,
    token: String?,
    navController: NavHostController,
) {
    val progress = if (target.amountNeeded > 0) {
        (target.amountCollected.toDouble() / target.amountNeeded).coerceIn(0.0, 1.0).toFloat()
    } else {
        0f
    }

    val showBottomSheet = remember { mutableStateOf(false) }

    if (showBottomSheet.value) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet.value = false },
            containerColor = White
        ) {
            TargetDetail(
                target = target,
                progress = progress,
                targetViewModel = targetViewModel,
                user = user,
                token = token ?: "",
                navController = navController,
                showBottomSheet = showBottomSheet
            )
        }
    }


    Card(
        modifier = modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        showBottomSheet.value = true
                    }
                )
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = target.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Black
                )
                Text(
                    text = CurrencyUtils.format(target.amountCollected) +
                            " / ${CurrencyUtils.format(target.amountNeeded)}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = YellowPrimary,
                trackColor = YellowTertiary,
                strokeCap = StrokeCap.Round,
            )
        }
    }
}
