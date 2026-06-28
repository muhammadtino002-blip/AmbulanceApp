package com.example.ambulanceapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ambulanceapp.ui.screens.AmbulanceDashboardScreen
import com.example.ambulanceapp.ui.screens.NonEmergencyScreen

@Composable
fun AmbulanceNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController    = navController,
        startDestination = Screen.Dashboard.route
    ) {

        composable(route = Screen.Dashboard.route) {
            AmbulanceDashboardScreen(
                onNavigateToNonEmergency = {
                    navController.navigate(Screen.NonEmergency.route)
                }
            )
        }

        composable(route = Screen.NonEmergency.route) {
            NonEmergencyScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

sealed class Screen(val route: String) {
    object Dashboard     : Screen("dashboard")
    object NonEmergency  : Screen("non_emergency")
}