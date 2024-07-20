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
import androidx.compose.ui.text.input.PasswordVisualTransformation
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
fun LoginScreen(navController: NavHostController, onLogin: (String, String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
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

            Text(
                text = "Welcome to SmartMavuno",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 12.dp)
                    .offset(x = 80.dp),
                color = green1
            )

            Text(
                text = "Sign In",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .offset(x = 170.dp),
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
                            placeholder = { Text("Email", color = green1) },
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

            // Password Field
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
                            value = password,
                            onValueChange = { password = it },
                            placeholder = { Text("Password", color = green1) },
                            trailingIcon = {
                                val icon = if (passwordVisible) {
                                    painterResource(id = R.drawable.baseline_remove_red_eye_24)
                                } else {
                                    painterResource(id = R.drawable.baseline_remove_red_eye_24)
                                }
                                Image(
                                    painter = icon,
                                    contentDescription = "Password Icon",
                                    modifier = Modifier
                                        .padding(horizontal = 12.dp)
                                        .clickable { passwordVisible = !passwordVisible },
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
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 0.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Forgot Password text
            ClickableText(
                text = AnnotatedString("Forgot Password?"),
                onClick = {
                    navController.navigate(Screens.Reset.screen)
                },
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = green1
                ),
                modifier = Modifier.padding(top = 8.dp, bottom = 6.dp, start = 250.dp)
            )

            // Login Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(color = green1)
                    .clickable {
                        navController.navigate(Screens.Home.screen)

                    }
            ) {
                Text(
                    text = "Sign In",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            // Social Media Icons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Google Icon
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(green1)
                        .padding(8.dp)
                        .clickable { /* Handle Gmail signup */ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "Gmail",
                        tint = Color.White
                    )
                }

                // Twitter Icon
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(green1)
                        .padding(8.dp)
                        .clickable { /* Handle Twitter signup */ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.twittter),
                        contentDescription = "Twitter",
                        tint = Color.White
                    )
                }

                // Facebook Icon
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(green1)
                        .padding(8.dp)
                        .clickable { /* Handle Facebook signup */ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = "Facebook",
                        tint = Color.White
                    )
                }
            }

            // Sign Up text
            ClickableText(
                text = AnnotatedString("\t\t\t Do not Have an Account Yet\n\t\t\t\t\t\t\t SignUp"),
                onClick = {
                    navController.navigate(Screens.Signup.screen)
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
fun LogInPreview() {
    val navController = rememberNavController()
    LoginScreen(navController = navController) { email, password ->
        // Handle signup logic here
    }
}
