package com.example.smartmavuno.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartmavuno.R
import com.example.smartmavuno.navigation.Screens
import com.example.smartmavuno.ui.theme.grey
import com.example.smartmavuno.ui.theme.white

@Composable
fun Community(navController: NavController) {
    val green1 = colorResource(id = R.color.green1)
    val green3 = colorResource(id = R.color.green3)
    val selectedButton = remember { mutableStateOf("Community") }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(green3)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Profile Picture
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
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Buttons Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        selectedButton.value = "Community"
                        // Handle Community Outreach button click
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedButton.value == "Community") green1 else grey
                    )
                ) {
                    Text(
                        text = "Community Outreach",
                        color = Color.Black
                    )
                }
                Button(
                    onClick = {

                        selectedButton.value = "Donations"
                        navController.navigate(Screens.Donate.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedButton.value == "Donations") green1 else grey
                    )
                ) {
                    Text(
                        text = "Donations",
                        color = Color.Black
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Communities",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    modifier = Modifier.padding(end = 150.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = "Forward",
                    modifier = Modifier
                        .width(40.dp)
                        .height(24.dp)
                        .clickable {

                        },
                    tint = green1
                )
            }

            // Top Communities
            val topCommunities = listOf(
                "SmartMavunars WorldWide",
                "SokoMoko Farmers"
            )
            val topCommunityIcons = listOf(
                R.drawable.centralkenyafarmers,
                R.drawable.centralkenyafarmers
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                topCommunities.forEachIndexed { index, community ->
                    TopCommunityBox(communityName = community, iconRes = topCommunityIcons[index], green1 = green1)
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(Color.White)
                    .padding(bottom = 26.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val communityIcons = listOf(
                    R.drawable.baseline_circle_24,
                    R.drawable.baseline_circle_24,
                    R.drawable.baseline_circle_24,
                    R.drawable.baseline_circle_24

                )
                val colors = listOf(
                    colorResource(id = R.color.green2),
                    colorResource(id = R.color.green1),
                    colorResource(id = R.color.green4),
                    colorResource(id = R.color.green3)
                )

                communityIcons.forEachIndexed { index, iconRes ->
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .padding(4.dp)
                            .offset(x = (-26).dp * index),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = iconRes),
                            contentDescription = "Community Icon $index",
                            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(colors[index]),
                            modifier = Modifier
                                .size(62.dp)
                                .clip(CircleShape)
                                .background(colors[index])
                                .padding(2.dp)
                        )
                    }
                }
            }




            // Other Communities
            val otherCommunities = listOf(
                "SoyBeans farmers",
                "Western Farmers Association",
                "Central Kenya Farmers Forum",
                "Smart Farmers Forum"
            )
            val communityIcons = listOf(
                R.drawable.centralkenyafarmers,
                R.drawable.centralkenyafarmers,
                R.drawable.centralkenyafarmers,
                R.drawable.centralkenyafarmers
            )



            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(green1)
                    .padding(16.dp)
            ) {
                Column {
                    // Add heading "COMMUNITIES"
                    Text(
                        text = "CONNECT WITH TOP COMMUNITIES",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        ),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(otherCommunities.size) { index ->
                            CommunityBox(
                                communityName = otherCommunities[index],
                                iconRes = communityIcons[index],
                                green1 = green1,
                                onClick = { /* Handle navigation to the community */ }
                            )
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun TopCommunityBox(communityName: String, iconRes: Int, green1: Color) {
    Box(
        modifier = Modifier
            .size(150.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(green1)
            .clickable {

            }
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = communityName,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Text(
            text = communityName,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(8.dp)
        )
    }
}

@Composable
fun CommunityBox(communityName: String, iconRes: Int, green1: Color, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(white)
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Image on the left side
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = communityName,
            modifier = Modifier
                .size(60.dp) // Adjust size to fit within the row
                .clip(CircleShape),
            contentScale = ContentScale.Crop // Make the image fill its space
        )

        // Spacer between the image and text
        Spacer(modifier = Modifier.width(16.dp))

        // Text displayed next to the image
        Text(
            text = communityName,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(1f)
        )

        // Arrow icon for navigation
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
            contentDescription = "Navigate to $communityName",
            modifier = Modifier
                .width(30.dp)
                .height(44.dp)
                .clickable { onClick() },
            tint = green1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CommunityScreenPreview() {
    Community(navController = rememberNavController())
}
