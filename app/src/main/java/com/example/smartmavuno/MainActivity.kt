package com.example.smartmavuno

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.smartmavuno.app.BottomNavComposable
import com.example.smartmavuno.navigation.SetupNavigation

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartMavunoApp()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SmartMavunoApp() {
    val navController = rememberNavController()
    BottomNavComposable(navController)
}





//@Composable
//fun SmartMavunoApp() {
//    val navController = rememberNavController()
//    SetupNavigation(navController = navController)
// BottomNavComposable(navController)
//}