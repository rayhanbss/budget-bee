package com.example.budgetbee.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val label: String,
    val iconFocused: ImageVector,
    val iconUnfocused: ImageVector,
)

