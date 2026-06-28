package com.example.ambulanceapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ambulanceapp.ui.screens.AmbulanceDashboardScreen
import com.example.ambulanceapp.ui.screens.NonEmergencyScreen
import com.example.ambulanceapp.ui.screens.OrderSuccessScreen
import com.example.ambulanceapp.ui.screens.SplashScreen

@Composable
fun AmbulanceNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController    = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(route = Screen.Splash.route) {
            SplashScreen(
                onSplashComplete = {
                    navController.navigate(Screen.Dashboard.route) {
                        // Remove Splash from back-stack so pressing Back exits the app
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screen.Dashboard.route) {
            AmbulanceDashboardScreen(
                onNavigateToNonEmergency = {
                    navController.navigate(Screen.NonEmergency.route)
                }
            )
        }

        composable(route = Screen.NonEmergency.route) {
            NonEmergencyScreen(
                onBack = { navController.popBackStack() },
                onOrderSubmitted = {
                    navController.navigate(Screen.OrderSuccsess.route)
                }
            )
        }

        composable(route = Screen.OrderSuccsess.route) {
            OrderSuccessScreen(
                onBackToDashboardClick = {
                    navController.navigate(Screen.Dashboard.route)
                }
            )
        }
    }
}

sealed class Screen(val route: String) {
    object Splash        : Screen("splash")
    object Dashboard     : Screen("dashboard")
    object NonEmergency  : Screen("non_emergency")
    object OrderSuccsess : Screen("order_succsess")
}