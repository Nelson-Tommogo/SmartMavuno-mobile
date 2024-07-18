package com.example.smartmavuno.app

import android.graphics.Color as AndroidColor
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartmavuno.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun Donate(navController: NavController) {
    val green1 = colorResource(id = R.color.green1)
    val green3 = colorResource(id = R.color.green3)
    val white = colorResource(id = R.color.white)
    val red = colorResource(id = R.color.red)
    var donationAmount by remember { mutableStateOf(0f) }
    var flowers by remember { mutableStateOf(listOf<Offset>()) }
    val coroutineScope = rememberCoroutineScope()

    val animatedWidth by animateFloatAsState(targetValue = donationAmount / 10000f, label = "")

    // Determine slider color based on the donation amount
    val sliderColors = SliderDefaults.colors(
        thumbColor = when {
            donationAmount <= 1000f -> red
            donationAmount <= 5500f -> Color(0xFF4CAF50) // Light green
            else -> green1 // Dark green
        },
        activeTrackColor = when {
            donationAmount <= 1000f -> red
            donationAmount <= 5500f -> Color(0xFF4CAF50) // Light green
            else -> green1 // Dark green
        },
        inactiveTrackColor = Color.Gray
    )

    // Function to generate falling flowers
    fun generateFlowers() {
        coroutineScope.launch {
            while (true) {
                if (donationAmount > 10000f) { // Only generate flowers if donation > $10k
                    val newFlower = Offset(Random.nextFloat() * 1000f, -50f)
                    flowers = flowers + newFlower
                }
                delay(200) // Adjust this delay as needed
            }
        }
    }

    // Start flower generation only when donation amount exceeds $10k
    if (donationAmount > 10000f) {
        LaunchedEffect(donationAmount) {
            generateFlowers()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(green1)
            .padding(16.dp)
    ) {
        // Main Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(green1)
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    text = "Donate",
                    style = MaterialTheme.typography.bodyMedium,
                    color = green1,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(green3)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .clip(RoundedCornerShape(8.dp))
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.logo_no_background),
                            contentDescription = "SmartMavuno Logo",
                            modifier = Modifier.size(128.dp),
                            tint = Color.Unspecified
                        )
                    }

                    // Bottom Progress Bar Slider
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp) // Height for the progress bar slider
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color.White)
                    ) {
                        Slider(
                            value = donationAmount,
                            onValueChange = {},
                            valueRange = 0f..10000f,
                            colors = sliderColors.copy(
                                thumbColor = Color.Transparent, // Hide thumb for progress bar
                                activeTrackColor = when {
                                    donationAmount <= 3000f -> red
                                    donationAmount <= 6500f -> Color(0xFF4CAF50) // Light green
                                    else -> green1 // Dark green
                                }
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Every donation helps us grow and support more farmers.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(green3)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Donate To Help The Vulnerable",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "You can Choose Your Donation\n Amount by Shifting the ball left \nand Right",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "$${donationAmount.toInt()}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Top Slider with dynamic color
                    Slider(
                        value = donationAmount,
                        onValueChange = { donationAmount = it },
                        valueRange = 0f..20000f,
                        colors = sliderColors,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            // Handle the button click here
                        },
                        shape = MaterialTheme.shapes.large,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF7C9A92) // Custom button color
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                    ) {
                        Text(
                            text = "Donate",
                            style = TextStyle(
                                fontSize = 22.sp,
                                fontWeight = FontWeight(500),
                                color = Color.White
                            )
                        )
                    }
                }
            }
        }

        // Falling flowers animation
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            flowers.forEach { flower ->
                Box(
                    modifier = Modifier
                        .offset(
                            x = flower.x.dp,
                            y = flower.y.dp
                        )
                        .size(24.dp)
                        .background(Color.Yellow) // Replace with your flower image or shape
                        .graphicsLayer {
                            alpha = 0.5f // Adjust alpha for transparency effect
                        }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DonationPagePreview() {
    Donate(navController = rememberNavController())
}

