package com.example.smartmavuno.auth

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.smartmavuno.R
import com.example.smartmavuno.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController, onAnimationEnd: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val green1 = colorResource(id = R.color.green1)

    LaunchedEffect(true) {
        // Zoom in
        expanded = true
        delay(1000)
        // Zoom out
        expanded = false
        delay(600)

        // Navigate to com.example.smartmavuno.app.onboard screen
        navController.navigate(Screens.Onboarding.screen)    }

    val transition = updateTransition(targetState = if (expanded) 1f else 0f, label = "scaleAndAlphaTransition")

    val scale by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 1000, easing = FastOutLinearInEasing)
        }, label = ""
    ) { it }

    val alpha by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 300, easing = LinearOutSlowInEasing)
        }, label = ""
    ) { it }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_no_background),
                contentDescription = "logo",
                modifier = Modifier
                    .width(700.dp)
                    .height(700.dp)
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        alpha = alpha
                    )
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    val navController = rememberNavController()
    SplashScreen(navController = navController) {}
}

