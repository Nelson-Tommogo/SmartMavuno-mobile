package com.example.smartmavuno

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartmavuno.app.BottomNavComposable
import com.example.smartmavuno.auth.LoginScreen
import com.example.smartmavuno.auth.ResetScreen
import com.example.smartmavuno.auth.SignupScreen
import com.example.smartmavuno.auth.SplashScreen
import com.example.smartmavuno.navigation.Screen
import onboard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            // Set up navigation
            SmartMavunoApp(navController = navController)
        }
    }
}

@Composable
fun SmartMavunoApp(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController){}
        }
        composable(Screen.Onboarding.route) {
            onboard(navController = navController)
        }
        composable(Screen.Signup.route) {
            SignupScreen(navController = navController) { s, s2 ->
                // Handle signup logic here
            }
        }

        composable(Screen.LogIn.route) {
            LoginScreen(navController = navController) { s, s2 ->
                // Handle signup logic here
            }
        }

        composable(Screen.Reset.route) {
            ResetScreen(navController = navController) { s,-> }
        }

        composable(Screen.BottomNav.route) {
            BottomNavComposable(navController = navController)
        }

    }
}
