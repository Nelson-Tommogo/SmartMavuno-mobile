package com.example.smartmavuno.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartmavuno.R
import com.example.smartmavuno.appActivities.showGeneralNotification
import com.example.smartmavuno.ui.theme.grey
import com.example.smartmavuno.ui.theme.white
import kotlinx.coroutines.delay

@Composable
fun HomePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(white)
    ) {
        NotificationBox()
        Spacer(modifier = Modifier.height(8.dp))
        Links()
        Spacer(modifier = Modifier.height(8.dp))
        RecentProducts()
        Spacer(modifier = Modifier.height(8.dp))
        Products()
    }
}



@Composable
fun NotificationBox() {
    val green1 = colorResource(id = R.color.green1)
    val green3 = colorResource(id = R.color.green3)
    val white = colorResource(id = R.color.white)
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(270.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(white),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .width(370.dp)
                .padding(16.dp)
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
                                    painter = painterResource(id = R.drawable.communitypic),
                                    contentDescription = "Profile Image",
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(25.dp))
                            Text(
                                text = "Hello SmartMavunar",
                                style = MaterialTheme.typography.bodyMedium,
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
                            painter = painterResource(id = R.drawable.notify),
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
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search") },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.search), // Replace with your search icon resource ID
                        contentDescription = "Search Icon"
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}







@Composable
fun Links() {
    val icons = listOf(
        Pair(R.drawable.farm, "Farm"),
        Pair(R.drawable.process, "Progress"),
        Pair(R.drawable.calendar, "Calendar"),
        Pair(R.drawable.forecast, "Weather")
    )

    Box(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(grey)
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
                    verticalArrangement = Arrangement.Center
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
fun RecentProducts() {
    val icons = listOf(
        R.drawable.crop,
        R.drawable.cropone,
        R.drawable.crop3,
        R.drawable.crop4,
        R.drawable.crop5
    )

    val green1 = colorResource(id = R.color.green1)
    val white = colorResource(id = R.color.white)
    var currentIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            currentIndex = (currentIndex + 1) % icons.size
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(grey)
            .padding(16.dp)
    ) {
        Text(
            text = "Recent com.example.smartmavuno.app.Products",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        val state = rememberLazyListState(initialFirstVisibleItemIndex = currentIndex)

        LazyRow(
            state = state,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            itemsIndexed(icons) { index, icon ->
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                        .clickable { /* Handle click */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        modifier = Modifier.size(58.dp),
                        tint = Color.Unspecified
                    )
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
            icons.forEachIndexed { index, _ ->
                val color = if (index == currentIndex) green1 else Color.Gray
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .padding(horizontal = 2.dp)
                        .background(color, shape = RoundedCornerShape(50))
                )
            }
        }
    }
}
@Composable
fun Products() {
    val icons = listOf(
        R.drawable.crop,
        R.drawable.cropone,
        R.drawable.crop3,
        R.drawable.crop4,
        R.drawable.crop5
    )

    val white = colorResource(id = R.color.white)

    var listState by remember { mutableIntStateOf(0) }

    // Function to scroll the LazyRow
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            listState = (listState + 1) % icons.size
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(grey)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Cultivation Methods",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "View more",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                modifier = Modifier.clickable {
                    //onclick for view more
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
            itemsIndexed(icons) { index, icon ->
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                        .clickable {
                                   /* Handle click */
                                   },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        modifier = Modifier.size(58.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HomePagePreview() {
    HomePage()

}
