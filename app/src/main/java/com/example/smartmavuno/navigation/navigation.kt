package com.example.smartmavuno.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.smartmavuno.auth.LoginScreen
import com.example.smartmavuno.auth.ResetScreen
import com.example.smartmavuno.auth.SignupScreen
import com.example.smartmavuno.auth.SplashScreen
import onboard

sealed class Screen(val route: String) {
    data object Onboarding : Screen("Splashonboard")
    data object Splash : Screen("splash")
    data object Signup : Screen("signUp")
    data object LogIn : Screen("login")
    data object Reset : Screen("resetpassword")
    data object BottomNav : Screen("navbar")
    data object HomeScreen : Screen("Home")
    data object Services : Screen("services")
    data object Community : Screen("Community")
    data object Articles : Screen("Articles")
}

@Composable
fun SetupNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        // Splash Screen
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController) {}
        }

        composable(Screen.Onboarding.route) {
            onboard(navController = navController)
        }

        // Sign Up Screen
        composable(Screen.Signup.route) {
            SignupScreen(navController = navController) { s, s2 -> }
        }

        // Sign In Screen
        composable(Screen.LogIn.route) {
            LoginScreen(navController = navController) { s, s2 -> }
        }

        // Reset Password Screen
        composable(Screen.Reset.route) {
            ResetScreen(navController = navController) { s -> }
        }

        // Bottom Navigation
        navigation(startDestination = Screen.HomeScreen.route, route = Screen.BottomNav.route) {
            composable(Screen.HomeScreen.route) {
                HomeScreen()
            }
            composable(Screen.Services.route) {
                ServicesScreen()
            }
            composable(Screen.Community.route) {
                CommunityScreen()
            }
            composable(Screen.Articles.route) {
                ArticlesScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    // Your Home screen content
}

@Composable
fun ServicesScreen() {
    // Your Services screen content
}

@Composable
fun CommunityScreen() {
    // Your Community screen content
}

@Composable
fun ArticlesScreen() {
    // Your Articles screen content
}
