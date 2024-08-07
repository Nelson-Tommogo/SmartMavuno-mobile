package com.example.smartmavuno.app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.smartmavuno.R
import com.example.smartmavuno.appActivities.showGeneralNotification
import com.example.smartmavuno.navigation.Screens
import com.example.smartmavuno.ui.theme.grey
import kotlinx.coroutines.delay
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Home(navController: NavHostController) {
    val verticalScrollState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(grey)
            .padding(16.dp),
        state = verticalScrollState
    ) {
        item { NotificationBox(navController) }
        item { Spacer(modifier = Modifier.height(8.dp)) }
        item { Links(navController) }
        item { Spacer(modifier = Modifier.height(8.dp)) }
        item { RecentProducts(navController) }
        item { Spacer(modifier = Modifier.height(8.dp)) }
        item { Methods(navController) }
        item { Spacer(modifier = Modifier.height(8.dp)) }
        item { Trending(navController)}
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationBox(navController: NavHostController) {
    val currentTime = remember { LocalTime.now() }
    val greeting = getGreeting(currentTime)
    val green1 = colorResource(id = R.color.green1)
    val green3 = colorResource(id = R.color.green3)
    val white = colorResource(id = R.color.white)
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(white),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(green1)
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Profile Image Holder
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(20.dp)) // Circular shape
                                    .background(green3) // Background color for the holder
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.profile),
                                    contentDescription = "Profile Image",
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(25.dp))
                            Text(
                                text = "Hello",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Spacer(modifier = Modifier.width(25.dp))
                            Text(
                                text = greeting,
                                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Welcome to SmartMavuno",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                    // Notification Icon at the top-right corner
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(0.dp),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_notifications_24),
                            contentDescription = "Notification",
                            tint = Color.White,
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    showGeneralNotification(
                                        context,
                                        "General Update",
                                        "This is a general notification"
                                    )
                                }
                        )
                    }
                }



            }


            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(color = Color.Gray)
                    .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .padding(horizontal = 0.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Search Services", color = green1) },
                        modifier = Modifier.weight(1f),
                        textStyle = TextStyle(color = Color.Black),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color.Black
                        )
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_search_24),
                        contentDescription = "Search Icon",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }



        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun getGreeting(currentTime: java.time.LocalTime): String {
    return when (currentTime.hour) {
        in 0..11 -> "Good Morning"
        in 12..16 -> "Good Afternoon"
        in 17..23 -> "Good Evening"
        else -> "Hello"
    }
}

@Composable
fun Links(navController: NavHostController) {
    val icons = listOf(
        Pair(R.drawable.farm, "Farm"),
        Pair(R.drawable.process, "Progress"),
        Pair(R.drawable.calendar, "Calendar"),
        Pair(R.drawable.forecast, "Weather"),
        Pair(R.drawable.marketplace, "Market Place")
    )

    Box(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            icons.forEach { icon ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.clickable {
                        when (icon.second) {
                            "Farm" -> {
                                navController.navigate(Screens.FarmsandServices.screen)
                            }
                            "Progress" -> {

                            }
                            "Calendar" -> {
                                navController.navigate(Screens.CalendarScreen.screen)

                            }
                            "Weather" -> {
                                navController.navigate(Screens.WeatherScreen.screen)

                            }
                            "Market Place" -> {
                                navController.navigate(Screens.MarketPlace.screen)

                            }
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = icon.first),
                        contentDescription = icon.second,
                        modifier = Modifier.size(36.dp),
                        tint = Color.Unspecified
                    )
                    Text(
                        text = icon.second,
                        color = Color.Black,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}



@Composable
fun RecentProducts(navController: NavHostController) {
    data class Product(
        val iconRes: Int,
        val name: String,
        val price: String,
        val discountedPrice: String,
        val rating: Int,
        var isInCart: Boolean = false // Add isInCart property
    )

    val products = listOf(
        Product(R.drawable.crop, "Fresh Carrots", "Ksh 100", "Ksh 80", 4),
        Product(R.drawable.cropone, "Tomatoes", "Ksh 200", "Ksh 150", 3),
        Product(R.drawable.crop3, "Mango Seeds", "Ksh 300", "Ksh 250", 5),
        Product(R.drawable.crop4, "Fresh Maize", "Ksh 400", "Ksh 350", 2),
        Product(R.drawable.crop5, "Cassava", "Ksh 500", "Ksh 450", 1)
    )

    val green1 = colorResource(id = R.color.green1)
    val white = colorResource(id = R.color.white)
    val grey = colorResource(id = R.color.grey)
    var currentIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            currentIndex = (currentIndex + 1) % products.size
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(white)
            .padding(16.dp)
    ) {
        Text(
            text = "Recent Products",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        val state = rememberLazyListState(initialFirstVisibleItemIndex = currentIndex)

        LazyRow(
            state = state,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), // Adjust height to accommodate "Add to Cart" button
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            itemsIndexed(products) { index, product ->
                Box(
                    modifier = Modifier
                        .width(150.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(grey)
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = product.iconRes),
                            contentDescription = null,
                            modifier = Modifier.size(38.dp),
                        )
                        Text(
                            text = product.name,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        Text(
                            text = product.price,
                            style = MaterialTheme.typography.bodySmall.copy(
                                textDecoration = TextDecoration.LineThrough
                            ),
                            color = Color.Red,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        Text(
                            text = product.discountedPrice,
                            style = MaterialTheme.typography.bodySmall,
                            color = green1,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        Row(
                            modifier = Modifier.padding(top = 4.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            repeat(5) { starIndex ->
                                val starColor = if (starIndex < product.rating) Color.Yellow else Color.Gray
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_star_purple500_24),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(12.dp)
                                        .padding(1.dp),
                                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(starColor)
                                )
                            }
                        }
                        Button(
                            onClick = {
                                product.isInCart = !product.isInCart
                                navController.navigate(Screens.MarketPlace.screen)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (product.isInCart) Color.Gray else green1
                            ),
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .defaultMinSize(minWidth = 64.dp, minHeight = 24.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                text = if (product.isInCart) "Remove" else "Add",
                                style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                                color = white
                            )
                        }
                    }
                }
            }
        }

        // Dots indicators
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            products.forEachIndexed { index, _ ->
                val color = if (index == currentIndex) green1 else Color.Gray
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .padding(horizontal = 2.dp)
                        .background(color, shape = CircleShape)
                )
            }
        }
    }
}






@Composable
fun Methods(navController: NavHostController) {
    data class Method(
        val iconRes: Int,
        val name: String
    )

    val methods = listOf(
        Method(R.drawable.crop, "Hydroponics"),
        Method(R.drawable.cropone, "Aquaponics"),
        Method(R.drawable.crop3, "Vertical Farming"),
        Method(R.drawable.crop4, "Greenhouse Farming"),
        Method(R.drawable.crop5, "Conservation Tillage")
    )

    val white = colorResource(id = R.color.white)
    val grey = colorResource(id = R.color.grey)

    var listState by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            listState = (listState + 1) % methods.size
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(white)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Popular Cultivation Methods",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "View more",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                modifier = Modifier.clickable {
                     navController.navigate(Screens.Articles.screen)
                }
            )
        }

        val state = rememberLazyListState(initialFirstVisibleItemIndex = listState)

        LazyRow(
            state = state,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            itemsIndexed(methods) { index, method ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(100.dp) // Adjust width to accommodate text
                        .clip(RoundedCornerShape(16.dp))
                        .background(grey)
                        .clickable { /* Handle click */ }
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = method.iconRes),
                        contentDescription = null,
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .padding(8.dp)
                    )
                    Text(
                        text = method.name,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}



@Composable
fun Trending(navController: NavHostController) {
    data class Provider(
        val iconRes: Int,
        val name: String
    )

    val providers = listOf(
        Provider(R.drawable.crop, "iProcure"),
        Provider(R.drawable.cropone, "M-Shamba"),
        Provider(R.drawable.crop3, "Yara East Africa"),
        Provider(R.drawable.crop4, "AgroCares"),
        Provider(R.drawable.crop5, "Hello Tractor")
    )

    val white = colorResource(id = R.color.white)
    val grey = colorResource(id = R.color.grey)

    var listState by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            listState = (listState + 1) % providers.size
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(white)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Top Service Providers",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "View more",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                modifier = Modifier.clickable {
                    // Handle click for "View more"
                }
            )
        }

        val state = rememberLazyListState(initialFirstVisibleItemIndex = listState)

        LazyRow(
            state = state,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            itemsIndexed(providers) { index, provider ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(80.dp) // Adjust width to accommodate text
                        .clip(RoundedCornerShape(16.dp))
                        .background(grey)
                        .clickable { /* Handle click */ }
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = provider.iconRes),
                        contentDescription = null,
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .padding(8.dp)
                    )
                    Text(
                        text = provider.name,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun HomePagePreview() {
    Home(navController = rememberNavController())

}
