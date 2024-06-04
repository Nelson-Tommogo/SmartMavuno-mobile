package com.example.smartmavuno.app

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartmavuno.R
import com.example.smartmavuno.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavComposable(navController: NavHostController) {
    val appbarcolor = colorResource(id = R.color.green1)
    val green2 = colorResource(id = R.color.green2)
    val green11 = colorResource(id = R.color.green1)

    val bottomAppBarHeight = 90.dp

    val items: List<Pair<Int, String>> = listOf(
        R.drawable.home to "Home",
        R.drawable.service to "Services",
        R.drawable.communityicon to "Community",
        R.drawable.articlesicon to "Articles"
    )

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(bottomAppBarHeight),
                contentColor = green2
            ) {
                items.forEachIndexed { index, (drawableRes, title) ->
                    IconButton(
                        onClick = {
                            selectedItemIndex = index
                            when (index) {
                                0 -> navController.navigate(Screen.HomeScreen.route)
                                1 -> navController.navigate(Screen.Services.route)
                                2 -> navController.navigate(Screen.Community.route)
                                3 -> navController.navigate(Screen.Articles.route)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(id = drawableRes),
                                contentDescription = title,
                                tint = if (selectedItemIndex == index) appbarcolor else green11
                            )
                            Spacer(modifier = Modifier.height((-15).dp))
                            Text(
                                text = title,
                                color = if (selectedItemIndex == index) appbarcolor else green11,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        },
        content = { padding ->
            AppNavigation(navController = navController, modifier = Modifier.padding(padding))
        }
    )
}

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route, modifier = modifier) {
        composable(Screen.HomeScreen.route) { HomePage() }
        composable(Screen.Services.route) { ServicePage() }
        composable(Screen.Community.route) { Community() }
        composable(Screen.Articles.route) { articles() }
    }
}



@Preview
@Composable
fun BottomNavComposablePreview() {
    val navController = rememberNavController()
    BottomNavComposable(navController = navController)
}
