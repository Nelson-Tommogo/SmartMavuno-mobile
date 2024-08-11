package com.example.smartmavuno.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.smartmavuno.app.*
import com.example.smartmavuno.auth.LoginScreen
import com.example.smartmavuno.auth.ResetScreen
import com.example.smartmavuno.auth.SignupScreen
import com.example.smartmavuno.auth.SplashScreen

sealed class Screens(val route: String) {
    data object Onboarding : Screens("Splashonboard")
    data object Splash : Screens("splash")
    data object Signup : Screens("SignupScreen")
    data object Login : Screens("LoginScreen")
    data object Reset : Screens("resetpassword")
    data object Navbar : Screens("BottomNavComposable")
    data object Onboard : Screens("onboard")
    data object Home : Screens("home")
    data object Service : Screens("service")
    data object Community : Screens("community")
    data object Articles : Screens("articles")
    data object FarmsandServices : Screens("FarmsandServices")
    data object WeatherScreen : Screens("WeatherScreen")
    data object MarketPlace : Screens("MarketPlace")
    data object Donate : Screens("Donate")
    data object Farm : Screens("Farms")
    data object AppSettings : Screens("appsettings")
    data object CalendarScreen : Screens("CalendarScreen")
    data object CreateEventScreen : Screens("CreateEventScreen")
    data object CreateArticleScreen : Screens("ArticleCreationScreen")
    data object PaymentScreen : Screens("PaymentOptions")
    data object Landowner : Screens("LandOwner")

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Splash.route) {
        composable(Screens.Splash.route) {
            SplashScreen(navController = navController) {
                navController.navigate(Screens.Onboarding.route) {
                    popUpTo(Screens.Splash.route) { inclusive = true }
                }
            }
        }

        composable(Screens.Onboarding.route) {
            onboard(navController = navController)
        }

        composable(Screens.Signup.route) {
            SignupScreen(navController = navController) { _, _, _, _ -> }
        }

        composable(Screens.Login.route) {
            LoginScreen(navController = navController) { _, _ -> }
        }

        composable(Screens.Reset.route) {
            ResetScreen(navController = navController) { _ -> }
        }

        composable(Screens.Navbar.route) {
            BottomNavComposable(navController = navController)
        }

        composable(Screens.FarmsandServices.route) {
            FarmsandServices(navController)
        }

        composable(Screens.Farm.route) {
            Farms(navController)
        }

        composable(Screens.MarketPlace.route) {
            MarketplaceScreen(navController)
        }

        composable(Screens.Onboard.route) {
            onboard(navController)
        }

        composable(Screens.CalendarScreen.route) {
            CalendarScreen(navController)
        }

        composable(Screens.Donate.route) {
            Donate(navController)
        }

        composable(Screens.CreateEventScreen.route) {
            CreateEventScreen(navController)
        }

        composable(Screens.CreateArticleScreen.route) {
            ArticleCreationScreen(navController) { }
        }

        composable(Screens.PaymentScreen.route) {
            PaymentOptions(navController)
        }
        composable(
            route = Screens.Landowner.route,
            arguments = listOf(
                navArgument("imageUri") { type = NavType.StringType },
                navArgument("videoUri") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val imageUri = backStackEntry.arguments?.getString("imageUri") ?: ""
            val videoUri = backStackEntry.arguments?.getString("videoUri") ?: ""
            LandOwner(navController, imageUri, videoUri)
        }
    }
}
