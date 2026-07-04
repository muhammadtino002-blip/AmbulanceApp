package com.example.ambulanceapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Design Tokens
private val NavyPrimary   = Color(0xFF1A3A6B)
private val White         = Color(0xFFFFFFFF)
private val TextSecondary = Color(0xFF6B7280)

// Bottom Nav Items
private data class BottomNavItem(
    val icon: ImageVector,
    val label: String
)

private val bottomNavItems = listOf(
    BottomNavItem(Icons.Outlined.Home,       "Home"),
    BottomNavItem(Icons.Outlined.Schedule, "History"),
    BottomNavItem(Icons.Outlined.Chat,     "Messages"),
    BottomNavItem(Icons.Outlined.Person,   "Profile")
)

@Composable
fun MainScreen(
    onNavigateToNonEmergency: () -> Unit = {}
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        containerColor = Color(0xFFF5F5F5),
        bottomBar = {
            NavigationBar(
                containerColor = White,
                tonalElevation = 0.dp
            ) {
                bottomNavItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected        = selectedTab == index,
                        onClick         = { selectedTab = index },
                        icon            = {
                            Icon(
                                imageVector        = item.icon,
                                contentDescription = item.label,
                                modifier           = Modifier.size(30.dp)
                            )
                        },
                        label           = {
                            Text(
                                text       = item.label,
                                fontSize   = 10.sp,
                                fontWeight = if (selectedTab == index)
                                    FontWeight.SemiBold else FontWeight.Normal
                            )
                        },
                        alwaysShowLabel = true,
                        colors          = NavigationBarItemDefaults.colors(
                            selectedIconColor   = NavyPrimary,
                            selectedTextColor   = NavyPrimary,
                            unselectedIconColor = TextSecondary,
                            unselectedTextColor = TextSecondary,
                            indicatorColor      = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            // Tab content swap
            when (selectedTab) {
                0 -> AmbulanceDashboardScreen(
                    onNavigateToNonEmergency = onNavigateToNonEmergency
                )
                1 -> HistoryScreen()
                2 -> PlaceholderScreen(title = "Messages")
                3 -> PlaceholderScreen(title = "Profile")
            }
        }
    }
}

// Placeholder for unbuilt tabs
@Composable
private fun PlaceholderScreen(title: String) {
    Box(
        modifier            = Modifier.fillMaxSize(),
        contentAlignment    = androidx.compose.ui.Alignment.Center
    ) {
        Text(
            text       = title,
            fontSize   = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color      = TextSecondary
        )
    }
}

// Preview
@Preview(
    name           = "Main Screen – Home tab",
    showBackground = true,
    device         = "spec:width=390dp,height=844dp,dpi=430"
)
@Composable
fun MainScreenPreview() {
    MaterialTheme { MainScreen() }
}