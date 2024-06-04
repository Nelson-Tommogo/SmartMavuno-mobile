
import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.smartmavuno.R

@SuppressLint("ComposableNaming")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun onboard(navController: NavController) {
    val buttoncolor = colorResource(id = R.color.green1)

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

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
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
                            .fillMaxHeight(0.55f),
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
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(26.dp)
            ) {
                // Skip button
                ClickableText(
                    text = buildAnnotatedString {
                        append("Skip ")
                        append("⏩")
                    },
                    onClick = {
                        if (currentPage > 0) {
                            currentPage--
                        } else {
                            navController.navigate("login")
                        }
                    }
                )



                Spacer(modifier = Modifier.width(26.dp))

                Button(
                    onClick = {
                        if (currentPage < onboardingData.size - 1) {
                            currentPage++
                        } else {
                            navController.navigate("navbar")
                        }
                    },
                ) {
                    Text(text = if (currentPage == onboardingData.size -1) "Start" else "Get Started")
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

@Preview
@Composable
fun OnboardingScreenPreview() {
    val navController = rememberNavController()
    onboard(navController = navController)
}
