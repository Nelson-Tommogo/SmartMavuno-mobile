package com.example.smartmavuno.navigation

import com.example.smartmavuno.app.MarketplaceScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smartmavuno.app.ArticleCreationScreen
import com.example.smartmavuno.auth.LoginScreen
import com.example.smartmavuno.auth.ResetScreen
import com.example.smartmavuno.auth.SignupScreen
import com.example.smartmavuno.auth.SplashScreen
import com.example.smartmavuno.app.BottomNavComposable
import com.example.smartmavuno.app.CalendarScreen
import com.example.smartmavuno.app.CreateEventScreen
import com.example.smartmavuno.app.Donate
import com.example.smartmavuno.app.Farms
import com.example.smartmavuno.app.FarmsandServices
import com.example.smartmavuno.app.PaymentOptions
import com.example.smartmavuno.app.onboard

sealed class Screens(val screen: String) {
    data object Onboarding : Screens("Splashonboard")
    data object Splash : Screens("splash")
    data object Signup : Screens("SignupScreen")
    data object Login : Screens("LoginScreen")
    data object Reset : Screens("resetpassword")
    data object navbar : Screens("BottomNavComposable")
    data object onboard :Screens("onboard")
    data object Home : Screens("home")
    data object Service : Screens("service")
    data object Community : Screens("community")
    data object Articles : Screens("articles")
    data object FarmsandServices : Screens("FarmsandServices")
    data object  WeatherScreen : Screens("WeatherScreen")
    data object  MarketPlace : Screens("MarketPlace")
    data object  Donate : Screens("Donate")
    data object  Farm : Screens("Farms")
    data object  appsettings : Screens ("appsettings")
    data object  CalendarScreen : Screens("CalendarScreen")
    data object  CreateEventScreen : Screens("CreateEventScreen")
    data object  CreateArticleScreen : Screens("ArticleCreationScreen")
    data object  PaymentScreen : Screens("PaymentOptions")


}

@RequiresApi(Build.VERSION_CODES.O)
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
            SignupScreen(navController = navController) { s, s2, s3, s4 ->
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

        composable(Screens.navbar.screen) {
            BottomNavComposable(navController = navController)
        }

        composable(Screens.FarmsandServices.screen){
            FarmsandServices(navController)
        }
        composable(Screens.Farm.screen){
            Farms(navController)
        }

        composable(Screens.MarketPlace.screen){
            MarketplaceScreen(navController)
        }
        composable(Screens.onboard.screen){
            onboard(navController)
        }

        composable(Screens.CalendarScreen.screen){
            CalendarScreen(navController)
        }

        composable(Screens.Donate.screen){
            Donate(navController)
        }
        composable(Screens.CreateEventScreen.screen){
            CreateEventScreen(navController)
        }
        composable(Screens.CreateArticleScreen.screen){
            ArticleCreationScreen(navController) {

            }
        }
        composable(Screens.PaymentScreen.screen){
            PaymentOptions(navController)
        }
    }
}
