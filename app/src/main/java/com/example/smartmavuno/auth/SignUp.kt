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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.smartmavuno.R
import com.example.smartmavuno.navigation.Screens
import com.example.smartmavuno.ui.theme.black
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(navController: NavHostController, param: (Any, Any, Any, Any) -> Unit) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var signUpMessage by remember { mutableStateOf("") }
    var showLoadingDialog by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var showFailureDialog by remember { mutableStateOf(false) }
    val auth = FirebaseAuth.getInstance()
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
            color = black
        )

        Text(
            text = "Sign Up",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 12.dp)
                .width(35.dp)
                .offset(x = 170.dp),
            color = black
        )

        // Username Field
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
                        value = username,
                        onValueChange = { username = it },
                        placeholder = { Text("Username", color = black) },
                        trailingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_supervised_user_circle_24),
                                contentDescription = "Username Icon",
                                modifier = Modifier.padding(horizontal = 12.dp),
                                colorFilter = ColorFilter.tint(green2)
                            )
                        },
                        textStyle = TextStyle(color = black),
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
                        placeholder = { Text("Email", color = black) },
                        trailingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_email_24),
                                contentDescription = "Email Icon",
                                modifier = Modifier.padding(horizontal = 12.dp),
                                colorFilter = ColorFilter.tint(green2)
                            )
                        },
                        textStyle = TextStyle(color = black),
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
                        placeholder = { Text("Password", color = black) },
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
                                colorFilter = ColorFilter.tint(green2)
                            )
                        },
                        textStyle = TextStyle(color = black),
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
                        placeholder = { Text("Confirm Password", color = black) },
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
                                colorFilter = ColorFilter.tint(green2)
                            )
                        },
                        textStyle = TextStyle(color = black),
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

        Spacer(modifier = Modifier.height(16.dp))

        // Sign Up Button
        Button(
            onClick = {
                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    signUpMessage = "All fields are required."
                    showFailureDialog = true
                } else if (password != confirmPassword) {
                    signUpMessage = "Passwords do not match."
                    showFailureDialog = true
                } else {
                    showLoadingDialog = true
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            showLoadingDialog = false
                            if (task.isSuccessful) {
                                showSuccessDialog = true
                                param(username, email, password, confirmPassword)
                            } else {
                                signUpMessage = "Sign Up Failed: ${task.exception?.message}"
                                showFailureDialog = true
                            }
                        }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = green2),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Sign Up",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = white
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // "Or Sign Up Using" Text
        Text(
            text = "Or Sign Up Using",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = black,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

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
                    .background(green2)
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
                    .background(green2)
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
                    .background(green2)
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

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ClickableText(
                text = AnnotatedString("Already have an account? Log In"),
                onClick = {
                    navController.navigate(Screens.Login.screen)
                },
                style = TextStyle(
                    color = black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }

    // Loading Dialog
    if (showLoadingDialog) {
        AlertDialog(
            onDismissRequest = {},
            confirmButton = {},
            dismissButton = {},
            title = { Text(text = "Signing Up...") },
            text = { Text(text = "Please wait while we create your account.") }
        )
    }

    // Success Dialog
    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        showSuccessDialog = false
                        navController.navigate(Screens.Login.screen)
                    }
                ) {
                    Text("OK")
                }
            },
            title = { Text(text = "Sign Up Successful") },
            text = { Text(text = "Your account has been created successfully!") }
        )
    }

    // Failure Dialog
    if (showFailureDialog) {
        AlertDialog(
            onDismissRequest = { showFailureDialog = false },
            confirmButton = {
                Button(
                    onClick = { showFailureDialog = false }
                ) {
                    Text("OK")
                }
            },
            title = { Text(text = "Sign Up Failed") },
            text = { Text(text = signUpMessage) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
    SignupScreen(navController = rememberNavController(), param = { _, _, _, _ -> })
}
