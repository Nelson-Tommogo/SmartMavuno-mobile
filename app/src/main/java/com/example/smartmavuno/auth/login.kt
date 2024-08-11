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
import com.example.smartmavuno.ui.theme.black
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController, onLogin: (String, String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val green1 = colorResource(id = R.color.green1)
    val green2 = colorResource(id = R.color.green2)
    val white = colorResource(id = R.color.white)
    val auth = FirebaseAuth.getInstance()

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("SmartMavuno") },
            text = { Text(dialogMessage) },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("OK")
                }
            }
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = white)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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
                    .padding(top = 4.dp, bottom = 12.dp),
                color = black,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Sign In",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 12.dp),
                color = black,
                textAlign = TextAlign.Center
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
                                cursorColor = black,
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
                                    painterResource(id = R.drawable.baseline_hide_source_24)
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

            // Forgot Password text
            ClickableText(
                text = AnnotatedString("Forgot Password?"),
                onClick = {
                    navController.navigate(Screens.Reset.route)
                },
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = black
                ),
                modifier = Modifier.padding(top = 8.dp, bottom = 6.dp, start = 250.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Login Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(color = green2)
                    .clickable {
                        coroutineScope.launch {
                            if (email.isNotEmpty() && password.isNotEmpty() && isValidEmail(email)) {
                                try {
                                    auth.signInWithEmailAndPassword(email, password)
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                dialogMessage = "User logged in successfully"
                                                showDialog = true
                                                navController.navigate(Screens.Navbar.route)
                                            } else {
                                                dialogMessage = task.exception?.message ?: "Login failed"
                                                showDialog = true
                                            }
                                        }
                                } catch (e: Exception) {
                                    dialogMessage = e.message ?: "An error occurred"
                                    showDialog = true
                                }
                            } else {
                                dialogMessage = "Please enter a valid email and password"
                                showDialog = true
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Sign In",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = white
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            ClickableText(
                text = AnnotatedString("Don't Have an Account Yet?"),
                onClick = {
                    navController.navigate(Screens.Signup.route)
                },
                style = TextStyle(
                    color = black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Thin
                ),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))

            ClickableText(
                text = AnnotatedString("Register Now"),
                onClick = {
                    navController.navigate(Screens.Signup.route)
                },
                style = TextStyle(
                    color = black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 6.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(rememberNavController()) { email, password ->
        // Handle login here
    }
}

