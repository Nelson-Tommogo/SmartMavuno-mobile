package com.example.smartmavuno

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.smartmavuno.app.BottomNavComposable
import com.example.smartmavuno.app.Home
import com.example.smartmavuno.navigation.SetupNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartMavunoApp()


        }
    }
}

@Composable
fun SmartMavunoApp() {
    val navController = rememberNavController()
    SetupNavigation(navController = navController)

}



//@Composable
//fun SmartMavunoApp() {
//    val navController = rememberNavController()
//    SetupNavigation(navController = navController)
// BottomNavComposable(navController)
//}