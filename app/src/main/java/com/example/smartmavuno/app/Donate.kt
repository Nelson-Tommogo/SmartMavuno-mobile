package com.example.smartmavuno.app

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.smartmavuno.ui.theme.black
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.random.Random

@Composable
fun Donate(navController: NavController) {
    val green1 = colorResource(id = R.color.green1)
    val green3 = colorResource(id = R.color.green3)
    val white = colorResource(id = R.color.white)
    val red = colorResource(id = R.color.red)
    var donationAmountFarmers by remember { mutableFloatStateOf(0f) }
    var donationAmountVulnerable by remember { mutableFloatStateOf(0f) }
    var emojis by remember { mutableStateOf(listOf<Offset>()) }
    val coroutineScope = rememberCoroutineScope()

    val animatedWidthFarmers by animateFloatAsState(targetValue = donationAmountFarmers / 10000f, label = "")
    val animatedWidthVulnerable by animateFloatAsState(targetValue = donationAmountVulnerable / 10000f, label = "")

    val emojiList = listOf("😊", "😂", "😍", "😎", "🤩", "😇", "🤗", "😋", "😜", "😝", "😛", "🤔", "🤨", "\uD83D\uDE43", "😏")

    // Determine slider color based on the donation amount
    val sliderColorsFarmers = SliderDefaults.colors(
        thumbColor = when {
            donationAmountFarmers <= 1000f -> red
            donationAmountFarmers <= 5500f -> Color(0xFF4CAF50) // Light green
            else -> green1 // Dark green
        },
        activeTrackColor = when {
            donationAmountFarmers <= 1000f -> red
            donationAmountFarmers <= 5500f -> Color(0xFF4CAF50) // Light green
            else -> green1 // Dark green
        },
        inactiveTrackColor = Color.Gray
    )

    val sliderColorsVulnerable = SliderDefaults.colors(
        thumbColor = when {
            donationAmountVulnerable <= 1000f -> red
            donationAmountVulnerable <= 5500f -> Color(0xFF4CAF50) // Light green
            else -> green1 // Dark green
        },
        activeTrackColor = when {
            donationAmountVulnerable <= 1000f -> red
            donationAmountVulnerable <= 5500f -> Color(0xFF4CAF50) // Light green
            else -> green1 // Dark green
        },
        inactiveTrackColor = Color.Gray
    )

    // Function to generate falling emojis
    fun generateEmojis() {
        coroutineScope.launch {
            while (true) {
                if (donationAmountFarmers > 5000f || donationAmountVulnerable > 5000f) { // Only generate emojis if donation > $5k
                    val newEmoji = Offset(Random.nextFloat() * 1000f, -50f)
                    emojis = emojis + newEmoji
                    Timber.tag("Donate").d("Generated emoji at: %s", newEmoji) // Debugging log
                }
                delay(100)
            }
        }
    }

    // Simulated function to fetch exchange rate
    suspend fun fetchExchangeRate(): Float {
        delay(1000) // Simulate network delay
        return 140f // Placeholder exchange rate (1 USD = 140 KES)
    }

    var exchangeRate by remember { mutableStateOf(140f) } // Initial exchange rate

    // Fetch exchange rate on start
    LaunchedEffect(Unit) {
        exchangeRate = fetchExchangeRate()
    }

    // Convert USD to KES
    fun convertToKES(amountInUSD: Float): Float {
        return amountInUSD * exchangeRate
    }

    // Start emoji generation only when donation amount exceeds $5k
    if (donationAmountFarmers > 5000f || donationAmountVulnerable > 5000f) {
        LaunchedEffect(donationAmountFarmers, donationAmountVulnerable) {
            Timber.tag("Donate").d("Donation amount exceeded 5,000") // Debugging log
            generateEmojis()
        }
    }



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
                    color = green3,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(370.dp)
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

                    // Bottom Progress Bar Slider for Farmers
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "$${donationAmountFarmers.toInt()} (${convertToKES(donationAmountFarmers).toInt()} KES)",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )

                        Slider(
                            value = donationAmountFarmers,
                            onValueChange = { donationAmountFarmers = it },
                            valueRange = 0f..10000f,
                            colors = sliderColorsFarmers.copy(
                                thumbColor = Color.Transparent, // Hide thumb for progress bar
                                activeTrackColor = when {
                                    donationAmountFarmers <= 3000f -> red
                                    donationAmountFarmers <= 6500f -> Color(0xFF4CAF50) // Light green
                                    else -> green1 // Dark green
                                }
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Every donation helps us grow and support more farmers. \uD83D\uDE4F",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {
                            // Handle the button click here
                        },
                        shape = MaterialTheme.shapes.large,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = green1 // Custom button color
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                    ) {
                        Text(
                            text = "Donate \uD83D\uDCB3",
                            style = TextStyle(
                                fontSize = 22.sp,
                                fontWeight = FontWeight(500),
                                color = Color.White
                            )
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(green3)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Emoji Slider
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "$${donationAmountVulnerable.toInt()} (${convertToKES(donationAmountVulnerable).toInt()} KES)",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )
                        Slider(
                            value = donationAmountVulnerable,
                            onValueChange = { donationAmountVulnerable = it },
                            valueRange = 0f..10000f,
                            colors = sliderColorsVulnerable.copy(
                                thumbColor = Color.Transparent, // Hide thumb for progress bar
                                activeTrackColor = when {
                                    donationAmountVulnerable <= 3000f -> red
                                    donationAmountVulnerable <= 6500f -> Color(0xFF4CAF50) // Light green
                                    else -> green1 // Dark green
                                }
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Your contribution makes a difference! \uD83D\uDE4C",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {
                            // Handle the button click here
                        },
                        shape = MaterialTheme.shapes.large,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = green1 // Custom button color
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                    ) {
                        Text(
                            text = "Donate \uD83D\uDCB3",
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

        // Emoji overlay
        emojis.forEach { emoji ->
            Text(
                text = emojiList.random(),
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 24.sp),
                modifier = Modifier
                    .graphicsLayer(
                        translationX = emoji.x,
                        translationY = emoji.y
                    )
            )
        }
    }


@Preview(showBackground = true)
@Composable
fun PreviewDonate() {
    Donate(navController = rememberNavController())
}
