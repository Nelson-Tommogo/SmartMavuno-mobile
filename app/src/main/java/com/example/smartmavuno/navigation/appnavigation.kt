package com.example.smartmavuno.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) { HomeScreen() }
        composable(Screen.Services.route) { ServicesScreen() }
        composable(Screen.Community.route) { CommunityScreen() }
        composable(Screen.Articles.route) { ArticlesScreen() }
    }
}
