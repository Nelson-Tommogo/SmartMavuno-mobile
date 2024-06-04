package com.example.smartmavuno.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.example.smartmavuno.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController, onLogin: (String, String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val green1 = colorResource(id = R.color.green1)
    val green2 = colorResource(id = R.color.green2)
    val white = colorResource(id = R.color.white)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = white)
            .padding(10.dp),
    )   {
        item {
            Image(
                painter = painterResource(id = R.drawable.logo_color),
                contentDescription = "logo",
                modifier = Modifier
                    .width(260.dp)
                    .aspectRatio(1f)
                    .clip(shape = MaterialTheme.shapes.extraSmall)
                    .offset(x = 43.dp)
                    .padding(bottom = 0.dp)
            )


            Text(
                text = "Welcome to SmartMavuno",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 6.dp, bottom = 16.dp)
                    .fillMaxWidth()
                    .offset(x = 70.dp),
                color = green1
            )

            Text(
                text = "Login",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .offset(x = 115.dp),
                color = green1
            )


            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    color = Color.LightGray,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .height(58.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("Email") },
                            leadingIcon = null, // Remove the leading icon from the OutlinedTextField
                            textStyle = TextStyle(color = green1),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                cursorColor = green2,
                                unfocusedLabelColor = green1,
                                focusedLabelColor = green1, // Set focused label color
                                unfocusedBorderColor = Color.LightGray, // Set unfocused border color
                                focusedBorderColor = green2, // Set focused border color
                                // Set transparent color for the focused border color to hide the outline when typing
                            ),
                            visualTransformation = VisualTransformation.None, // Show the actual text
                            placeholder = { Text("Email") }, 
                            modifier = Modifier
                                .weight(1f)
                                .padding(vertical = 8.dp) // Adjust vertical padding
                        )
                        Image(
                            painter = painterResource(id = R.drawable.emailsmart), // Use the drawable from the drawables folder
                            contentDescription = "Email Icon",
                            modifier = Modifier.padding(end = 16.dp)
                        )
                    }
                }
            }



            Spacer(modifier = Modifier.height(16.dp))


            // Password Field
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    color = Color.LightGray,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .height(58.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Password") },
                            leadingIcon = null, // Remove the leading icon from the OutlinedTextField
                            textStyle = TextStyle(color = green1),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                cursorColor = green2,
                                unfocusedLabelColor = green1,
                                focusedLabelColor = green1, // Set focused label color
                                unfocusedBorderColor = Color.LightGray, // Set unfocused border color
                                focusedBorderColor = green2, // Set focused border color
                            ),
                            visualTransformation = PasswordVisualTransformation(),
                            modifier = Modifier
                                .weight(1f)
                                .padding(bottom = 16.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.passwordsmart), // Use the drawable from the drawables folder
                            contentDescription = "Password Icon",
                            modifier = Modifier.padding(end = 16.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))


            ClickableText(
                text = AnnotatedString("Forgot Password?"),
                onClick = {
                    navController.navigate(Screen.Reset.route)
                },
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = green1
                ),
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 6.dp, start = 275.dp)
            )


         //login button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp) // Set the button height
                    .padding(horizontal = 16.dp) // Add horizontal padding
                    .clip(RoundedCornerShape(25.dp)) // Apply rounded corners with a 12dp radius
                    .background(color = green1) // Set the background color
                    .clickable { /* Handle button click */ }
            ) {
                Text(
                    text = "Sign In",
                    color = Color.White, // Set text color
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }



            // Social Media Icons
            // Social Media Icons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
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




            ClickableText(
                text = AnnotatedString("Do not have acoount yet?\n Sign Up"),
                onClick = {
                    navController.navigate(Screen.Signup.route)
                },
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = green1
                ),
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp, start = 135.dp)
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



