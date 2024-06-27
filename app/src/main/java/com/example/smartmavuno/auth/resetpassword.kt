package com.example.smartmavuno.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.smartmavuno.R
import com.example.smartmavuno.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetScreen(navController: NavHostController, onLogin: (String) -> Unit) {
    var email by remember { mutableStateOf("") }
    val green1 = colorResource(id = R.color.green1)
    val green2 = colorResource(id = R.color.green2)
    val white = colorResource(id = R.color.white)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = white)
            .padding(10.dp),
    ) {
        item {
            Spacer(modifier = Modifier.height(130.dp))

            Image(
                painter = painterResource(id = R.drawable.logo_color),
                contentDescription = "logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .aspectRatio(1f)
                    .clip(shape = MaterialTheme.shapes.extraLarge)
                    .offset(x = 5.dp)
                    .padding(bottom = 0.dp)
            )


            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = "Forgot Password?",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .offset(x = 130.dp),
                color = green1
            )

            // Email Field
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    color = Color.LightGray,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .height(68.dp)
                        .padding(horizontal = 6.dp)
                        .padding(vertical = 5.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextField(
                            value = email,
                            onValueChange = { email = it },
                            placeholder = { Text("Email Address", color = green1) },
                            trailingIcon = {
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_email_24),
                                    contentDescription = "Email Icon",
                                    modifier = Modifier.padding(horizontal = 12.dp),
                                    colorFilter = ColorFilter.tint(green1)
                                )
                            },
                            textStyle = TextStyle(color = green1),
                            colors = TextFieldDefaults.textFieldColors(
                                cursorColor = green2,
                                containerColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent
                            ),
                            visualTransformation = VisualTransformation.None,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 0.dp)
                        )
                    }
                }
            }



            Spacer(modifier = Modifier.height(12.dp))

            // reset Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(color = green1)
                    .clickable {
                        navController.navigate(Screens.Login.screen)
                    }
            ) {
                Text(
                    text = "Generate Password",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }



            // Sign Up text
            ClickableText(
                text = AnnotatedString("\t\t\t Remembered Your Account?\n\t\t\t\t\t Go Back and Login"),
                onClick = {
                    navController.navigate(Screens.Login.screen)
                },
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = green1
                ),
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 110.dp)
            )
        }
    }
}

// Function to validate email format
private fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@Preview
@Composable
fun ResetScreenPreview()  {
    val navController = rememberNavController()
    ResetScreen(navController = navController) { email ->
        // Handle reset logic here
    }
}
