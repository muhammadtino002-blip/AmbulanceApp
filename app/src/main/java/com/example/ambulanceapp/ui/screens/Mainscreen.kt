package com.example.ambulanceapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ambulanceapp.data.AmbulanceOrder
import com.example.ambulanceapp.ui.components.UnderOptimizationDialog
import com.example.ambulanceapp.ui.theme.White

// Bottom Nav Items
private data class BottomNavItem(
    val icon: ImageVector,
    val label: String,
    val isPlaceholder: Boolean = false
)

private val bottomNavItems = listOf(
    BottomNavItem(Icons.Outlined.Home,       "Home"),
    BottomNavItem(Icons.Outlined.Schedule, "History"),
    BottomNavItem(Icons.Outlined.Chat,     "Messages", isPlaceholder = true),
    BottomNavItem(Icons.Outlined.Person,   "Profile")
)

@Composable
fun MainScreen(
    onNavigateToNonEmergency: () -> Unit = {},
    onNavigateToEmergency: () -> Unit = {},
    onNavigateToHistoryDetail: (AmbulanceOrder) -> Unit = {},
    onLogOut: () -> Unit = {}
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    var showOptimizationDialog by remember { mutableStateOf(false) }

    if (showOptimizationDialog) {
        UnderOptimizationDialog(onDismiss = { showOptimizationDialog = false })
    }

    Scaffold(
        containerColor = Color(0xFFF5F5F5),
        bottomBar = {
            FloatingPillNavBar(
                items           = bottomNavItems,
                selectedIndex   = selectedTab,
                onItemSelected  = { index, item ->
                    if (item.isPlaceholder) {
                        showOptimizationDialog = true
                    } else {
                        selectedTab = index
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
//            .padding(innerPadding)
        ) {
            // Tab content swap
            when (selectedTab) {
                0 -> AmbulanceDashboardScreen(
                    onNavigateToEmergency = onNavigateToEmergency,
                    onNavigateToNonEmergency = onNavigateToNonEmergency,
                )
                1 -> HistoryScreen(onOrderClick = onNavigateToHistoryDetail)
                3 -> ProfileScreen(onLogOut = onLogOut)
                else -> AmbulanceDashboardScreen (
                    onNavigateToEmergency = onNavigateToEmergency,
                    onNavigateToNonEmergency = onNavigateToNonEmergency,
                )
            }
        }
    }
}

@Composable
private fun FloatingPillNavBar(
    items: List<BottomNavItem>,
    selectedIndex: Int,
    onItemSelected: (index: Int, item: BottomNavItem) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 32.dp, vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .shadow(
                    elevation    = 12.dp,
                    shape        = RoundedCornerShape(50.dp),
                    ambientColor = Color.Black.copy(alpha = 0.08f),
                    spotColor    = Color.Black.copy(alpha = 0.12f)
                )
                .clip(RoundedCornerShape(50.dp))
                .background(White)
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items.forEachIndexed { index, item ->
                val isSelected = selectedIndex == index

                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication        = null
                        ) { onItemSelected(index, item) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector        = item.icon,
                        contentDescription = item.label,
                        tint               = if (isSelected) Color(0xFF122850) else Color(0xFFAAAAAA),
                        modifier           = Modifier.size(32.dp),
                    )
                }
            }
        }
    }
}

// Preview
@Preview(
    name           = "Main Screen",
    showBackground = true,
    device         = "spec:width=390dp,height=844dp,dpi=430"
)
@Composable
fun MainScreenPreview() {
    MaterialTheme {
        MainScreen()
    }
}