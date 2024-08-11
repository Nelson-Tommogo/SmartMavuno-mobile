package com.example.smartmavuno.app

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartmavuno.R
import com.example.smartmavuno.auth.LoginScreen
import com.example.smartmavuno.auth.SignupScreen
import com.example.smartmavuno.navigation.Screens
import com.example.smartmavuno.ui.theme.white

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavComposable(navController: NavHostController) {
    val green2 = colorResource(id = R.color.green2)
    val grey = colorResource(id = R.color.grey)
    val bottomAppBarHeight = 70.dp

    var selected by remember { mutableStateOf("Home") }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = grey,
                modifier = Modifier
                    .height(bottomAppBarHeight)
                    .padding(horizontal = 25.dp, vertical = 5.dp)
                    .clip(shape = RoundedCornerShape(13.dp)),
                contentColor = green2
            ) {
                IconButton(
                    onClick = {
                        selected = "Home"
                        navController.navigate(Screens.Home.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "HomeIcon",
                            modifier = Modifier.size(26.dp),
                            tint = if (selected == "Home") white else colorResource(id = R.color.green2)
                        )
                        Text(
                            text = "Home",
                            fontSize = 12.sp,
                            color = if (selected == "Home") white else colorResource(id = R.color.green2),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                IconButton(
                    onClick = {
                        selected = "com.example.smartmavuno.model.Service"
                        navController.navigate(Screens.Service.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_miscellaneous_services_24),
                            contentDescription = "ServiceIcon",
                            modifier = Modifier.size(26.dp),
                            colorFilter = if (selected == "com.example.smartmavuno.model.Service") ColorFilter.tint(Color.White) else ColorFilter.tint(green2)
                        )
                        Text(
                            text = "Services",
                            fontSize = 12.sp,
                            color = if (selected == "com.example.smartmavuno.model.Service") Color.White else green2,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                IconButton(
                    onClick = {
                        selected = "Community"
                        navController.navigate(Screens.Community.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_people_24),
                            contentDescription = "CommunityIcon",
                            modifier = Modifier.size(26.dp),
                            colorFilter = if (selected == "Community") ColorFilter.tint(Color.White) else ColorFilter.tint(green2)
                        )
                        Text(
                            text = "Community",
                            fontSize = 12.sp,
                            color = if (selected == "Community") Color.White else green2,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                IconButton(
                    onClick = {
                        selected = "Articles"
                        navController.navigate(Screens.Articles.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_article_24),
                            contentDescription = "ArticlesIcon",
                            modifier = Modifier.size(26.dp),
                            colorFilter = if (selected == "Articles") ColorFilter.tint(Color.White) else ColorFilter.tint(green2)
                        )
                        Text(
                            text = "Articles",
                            fontSize = 12.sp,
                            color = if (selected == "Articles") Color.White else green2,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screens.Home.route) { Home(navController) }
            composable(Screens.Service.route) { Service(navController) }
            composable(Screens.Community.route) { Community(navController) }
            composable(Screens.Articles.route) { Articles(navController) }
            composable(Screens.FarmsandServices.route) { FarmsandServices(navController) }
            composable(Screens.WeatherScreen.route) { WeatherScreen(navController) }
            composable(Screens.MarketPlace.route) { MarketplaceScreen(navController) }
            composable(Screens.AppSettings.route) { SettingsScreen(navController) }
            composable(Screens.Donate.route) { Donate(navController) }
            composable(Screens.Landowner.route) {
                LandOwner(
                    navController = navController,
                    onImageOrVideoCaptured = { imageOrVideoUrl: (String) -> Unit ->
                    }.toString(),
                    onSubmit = {
                    }.toString()
                )
            }


            composable(Screens.Onboard.route) { onboard(navController) }
            composable(Screens.CalendarScreen.route) { CalendarScreen(navController) }
            composable(Screens.CreateEventScreen.route) { CreateEventScreen(navController) }
            composable(Screens.CreateArticleScreen.route) { ArticleCreationScreen(navController){}
            }
            composable(Screens.PaymentScreen.route) { PaymentOptions(navController) }
            composable(Screens.Farm.route) { Farms(navController) }
            composable(Screens.Navbar.route) { BottomNavComposable(navController) }
            composable(Screens.Login.route) {
                LoginScreen(navController)
            }
            composable(Screens.Signup.route) { SignupScreen(navController){
                    s, s2, s3, s4 ->
            } }        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun BottomNavComposablePreview() {
    val navController = rememberNavController()
    BottomNavComposable(navController = navController)
}
