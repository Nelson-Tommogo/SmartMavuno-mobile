package com.example.smartmavuno.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smartmavuno.auth.LoginScreen
import com.example.smartmavuno.auth.ResetScreen
import com.example.smartmavuno.auth.SignupScreen
import com.example.smartmavuno.auth.SplashScreen
import com.example.smartmavuno.app.BottomNavComposable
import com.example.smartmavuno.app.onboard

sealed class Screens(val screen: String) {
    data object Onboarding : Screens("Splashonboard")
    data object Splash : Screens("splash")
    data object Signup : Screens("signUp")
    data object Login : Screens("login")
    data object Reset : Screens("resetpassword")
    data object BottomNav : Screens("navbar")
    data object Home : Screens("home")
    data object Service : Screens("service")
    data object Community : Screens("community")
    data object Articles : Screens("articles")
}

@Composable
fun SetupNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Splash.screen) {
        composable(Screens.Splash.screen) {
            SplashScreen(navController = navController) {
                navController.navigate(Screens.Onboarding.screen) {
                    popUpTo(Screens.Splash.screen) { inclusive = true }
                }
            }
        }

        composable(Screens.Onboarding.screen) {
            onboard(navController = navController)
        }

        composable(Screens.Signup.screen) {
            SignupScreen(navController = navController) { s, s2, s3 ->
            }
        }

        composable(Screens.Login.screen) {
            LoginScreen(navController = navController) { s, s2 ->
            }
        }

        composable(Screens.Reset.screen) {
            ResetScreen(navController = navController) { s ->
            }
        }

        composable(Screens.BottomNav.screen) {
            BottomNavComposable(navController = navController)
        }
    }
}
