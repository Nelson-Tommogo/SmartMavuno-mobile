package com.example.smartmavuno.app



import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.smartmavuno.R
import com.example.smartmavuno.navigation.Screens
import com.example.smartmavuno.ui.theme.green1
import com.example.smartmavuno.ui.theme.green3
import com.example.smartmavuno.ui.theme.white

@SuppressLint("ComposableNaming")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun onboard(navController: NavController) {

    val onboardingData = listOf(
        OnboardingItem(
            title = "Welcome to SmartMavuno \uD83C\uDF31",
            description = "Discover the best agricultural practices advocated by smartmavuno. We connect you directly to buyers, eliminating middlemen, we give you weather updates, real-time farming market trends, Access to different farming services such connect you directly to fertilizer providers, and other services \uD83D\uDE9C\uD83C\uDF27\uFE0F",
            imageRes = R.drawable.restwo
        ),
        OnboardingItem(
            title = "Services at your fingertips 📱",
            description = "Check out our amazing services on our platform and enjoy! 🎉 We offer a wide range of agricultural tools and resources to help you succeed in your farming journey. From crop monitoring to market analysis, we've got you covered. Join us today and take your farming to the next level! 🚀",
            imageRes = R.drawable.resthree
        ),
        OnboardingItem(
            title = "Join the community 🌍👩‍🌾",
            description = "Connect with like-minded farmers and customers across the globe to share experiences! 🤝🌱 Get valuable insights, tips, and support from our vibrant community. Together, we grow better! 🚀",
            imageRes = R.drawable.com
        )
    )

    var currentPage by remember { mutableIntStateOf(0) }
    val context = LocalContext.current

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White) // Background color of the box
                .padding(24.dp) // Padding around the content
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {

                        val toast = Toast.makeText(
                            context,
                            "Please Reach Out to Our Team for Onboarding Assistance, Contact smartmavuno@gmail.com or +254759735505",
                            Toast.LENGTH_LONG
                        )
                        toast.show()

                        Handler(Looper.getMainLooper()).postDelayed({
                            toast.cancel()
                        }, 85000)
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(colorResource(id = R.color.green1))
                        .align(Alignment.End)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_info_outline_24),
                        contentDescription = stringResource(id = R.string.info_icon),
                        tint = white
                    )
                }

                HorizontalPager(
                    modifier = Modifier.weight(1f),
                    state = rememberPagerState(pageCount = { onboardingData.size }).apply {
                        // Animate to the selected page when currentPage changes
                        LaunchedEffect(currentPage) {
                            animateScrollToPage(currentPage)
                        }
                    }
                ) { page ->
                    val item = onboardingData[page]

                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Call rememberImagePainter outside remember
                        val imagePainter = rememberImagePainter(data = item.imageRes)

                        // Now you can use imagePainter in your UI
                        Image(
                            painter = imagePainter,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.55f)
                                .clip(RoundedCornerShape(20.dp)), // Rounded corners
                            contentScale = ContentScale.FillWidth
                        )

                        Column(
                            verticalArrangement = Arrangement.spacedBy(26.dp),
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp)
                            )
                            Text(
                                text = item.description,
                                style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp)
                            )
                            // Indicator for showing current page position
                            Indicator(
                                currentPage = page,
                                pageCount = onboardingData.size,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }

                    }
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                ) {
                    // Container for Skip and Get Started buttons
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp))
                            .background(green1)
                            .padding(vertical = 1.dp) // Adjusted vertical padding
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 2.dp) // Adjusted horizontal padding
                        ) {
                            // Skip button aligned to the start (left)
                            Box(
                                modifier = Modifier
                                    .height(36.dp) // Adjusted height
                                    .clip(RoundedCornerShape(18.dp)) // Rounded corners
                                    .background(green3)
                                    .padding(horizontal = 18.dp) // Adjusted horizontal padding
                                    .align(Alignment.CenterVertically) // Align vertically in the parent Box
                            ) {
                                ClickableText(
                                    text = buildAnnotatedString {
                                        append("Skip ")
                                        append("⏩")
                                    },
                                    onClick = {
                                        navController.navigate(Screens.Login.screen)
                                    },
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }

                            // Spacer to separate Skip and Next buttons
                            Spacer(modifier = Modifier.width(8.dp))

                            // Get Started button aligned to the end (right)
                            Button(
                                onClick = {
                                    if (currentPage < onboardingData.size - 1) {
                                        currentPage++
                                    } else {
                                        navController.navigate(Screens.BottomNav.screen)
                                    }
                                },
                                shape = MaterialTheme.shapes.large,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = green3
                                ),
                                modifier = Modifier.align(Alignment.CenterVertically)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = if (currentPage == onboardingData.size - 1) "Start" else "Next",
                                        color = Color.Black
                                    )
                                    // Emoji of a person walking
                                    Text(
                                        text = " 🚶‍♂️",
                                        fontSize = 20.sp,
                                        modifier = Modifier.padding(start = 4.dp)
                                    )
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}

data class OnboardingItem(
    val title: String,
    val description: String,
    val imageRes: Int
)


@Composable
fun InfoDialog() {
    val infoText = """
        Welcome to SmartMavuno!
        
        For more information or assistance, please contact:
        Name: John Doe
        Email: john.doe@smartmavuno.com
        Phone: +123456789
    """.trimIndent()

    AlertDialog(
        onDismissRequest = { /* Dismiss if needed */ },
        title = { Text(text = "SmartMavuno Info") },
        text = {
            Text(
                text = infoText,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        },
        confirmButton = {
            Button(
                onClick = { /* Dismiss dialog if needed */ }
            ) {
                Text(text = "Close")
            }
        }
    )
}


@Composable
fun Indicator(
    currentPage: Int,
    pageCount: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(pageCount) { index ->
            Box(
                modifier = Modifier
                    .size(if (index == currentPage) 12.dp else 8.dp)
                    .clip(CircleShape)
                    .background(color = if (index == currentPage) Color.Black else Color.Gray)
            )
        }
    }
}


@Preview
@Composable
fun OnboardPreview() {
    val navController = rememberNavController()
    onboard(navController = navController)
}
