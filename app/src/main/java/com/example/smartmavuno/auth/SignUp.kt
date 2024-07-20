package com.example.smartmavuno.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
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
fun SignupScreen(navController: NavHostController, onSignup: (String, String, String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val green1 = colorResource(id = R.color.green1)
    val green2 = colorResource(id = R.color.green2)
    val white = colorResource(id = R.color.white)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = white)
            .padding(10.dp)
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        Image(
            painter = painterResource(id = R.drawable.logo_color),
            contentDescription = "logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .aspectRatio(1f)
                .clip(shape = MaterialTheme.shapes.extraLarge)
                .offset(x = 5.dp)
                .padding(bottom = 0.dp)
        )
        Spacer(modifier = Modifier.height(40.dp))
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
            text = "Sign Up",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 12.dp)
                .width(35.dp)
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

        // Confirm Password Field
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
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        placeholder = { Text("Confirm Password", color = green1) },
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
        Spacer(modifier = Modifier.height(20.dp))
        // Signup Button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(color = green1)
                .clickable {
                    // Check if the password and confirm password match
                    if (password == confirmPassword) {
                        // Call the onSignup function with email, password, and confirmPassword
                        onSignup(email, password, confirmPassword)
                        navController.navigate(Screens.Login.screen)
                    } else {
                        // Navigate to the login screen if passwords don't match
                        navController.navigate(Screens.Login.screen)
                    }
                }
        ) {
            // Optionally, you can add content inside the Box
            // For example, a Text composable
            Text(
                text = "Sign Up",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
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

        // Sign In text
        ClickableText(
            text = AnnotatedString("\t\t\tAlready Have An Account?\n\t\t\t\t\t\t\t Sign In"),
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

// Function to validate email format
private fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@Preview
@Composable
fun ScreensPreview() {
    val navController = rememberNavController()
    SignupScreen(navController = navController) { email, password, confirmpassword ->
    }
}
