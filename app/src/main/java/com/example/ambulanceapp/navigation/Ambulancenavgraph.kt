package com.example.ambulanceapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ambulanceapp.ui.screens.DetailHistoryScreen
import com.example.ambulanceapp.ui.screens.EmergencyScreen
import com.example.ambulanceapp.ui.screens.HistoryScreen
import com.example.ambulanceapp.ui.screens.MainScreen
import com.example.ambulanceapp.ui.screens.NonEmergencyScreen
import com.example.ambulanceapp.ui.screens.OrderSuccessScreen
import com.example.ambulanceapp.ui.screens.SignInScreen
import com.example.ambulanceapp.ui.screens.SignUpScreen
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
                    navController.navigate(Screen.SignIn.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screen.SignIn.route) {
            SignInScreen(
                onSignInClick = { username, password ->
                    navController.navigate(route = Screen.Dashboard.route)
                },
                onSignUpClick = {
                    navController.navigate(route = Screen.SignUp.route)
                }
            )
        }

        composable(route = Screen.SignUp.route) {
            SignUpScreen(
                onSignUpClick = {
                    navController.navigate(Screen.Dashboard.route)
                },
                onBackClick = {
                    navController.navigate(Screen.SignIn.route)
                }
            )
        }

        composable(Screen.Dashboard.route) {
            MainScreen(
                onNavigateToNonEmergency = {
                    navController.navigate(Screen.NonEmergency.route)
                },
                onNavigateToEmergency = {
                    navController.navigate(Screen.Emergency.route)
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

        composable(route = Screen.Emergency.route) {
            EmergencyScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = Screen.OrderSuccsess.route) {
            OrderSuccessScreen(
                onBackToDashboardClick = {
                    navController.navigate(Screen.Dashboard.route)
                }
            )
        }

        composable(route = Screen.HistoryScreen.route) {
            HistoryScreen(

            )
        }

        composable(route = Screen.DetailHistory.route) {
            DetailHistoryScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

sealed class Screen(val route: String) {
    object Splash        : Screen("splash")
    object SignIn        : Screen("signin")
    object SignUp        : Screen("signup")
    object Dashboard     : Screen("dashboard")
    object NonEmergency  : Screen("non_emergency")
    object Emergency     : Screen("emergency")
    object OrderSuccsess : Screen("order_succsess")
    object HistoryScreen : Screen("history_screen")
    object DetailHistory : Screen("detail_history")
}