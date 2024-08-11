package com.example.smartmavuno.auth

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(navController: NavHostController, param: (Any, Any, Any, Any) -> Unit) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var signUpMessage by remember { mutableStateOf("") }
    var showLoadingDialog by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var showFailureDialog by remember { mutableStateOf(false) }
    var accountType by remember { mutableStateOf("Basic User") }
    var serviceProviderName by remember { mutableStateOf("") }
    var facilityName by remember { mutableStateOf("") }
    var agencyName by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }


    val auth = FirebaseAuth.getInstance()
    val firestore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
    val green2 = colorResource(id = R.color.green2)
    val white = colorResource(id = R.color.white)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = white)
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)
                .background(Color.LightGray)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(48.dp)
                    .padding(start = 1.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
        }

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

        // Username FieldF
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

        // Phone Number Field
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
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        placeholder = { Text("Phone Number", color = black) },
                        trailingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_local_phone_24),
                                contentDescription = "Phone Number Icon",
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
                                painterResource(id = R.drawable.baseline_hide_source_24)
                            } else {
                                painterResource(id = R.drawable.baseline_remove_red_eye_24)
                            }
                            Image(
                                painter = icon,
                                contentDescription = "Confirm Password Icon",
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

        // Account Type Selector
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                .padding(8.dp)
        ) {
            Column {
                Text(
                    text = "Select Account Type:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    RadioButton(
                        selected = accountType == "Basic User",
                        onClick = { accountType = "Basic User" },
                        colors = RadioButtonDefaults.colors(green2)
                    )
                    Text(text = "Basic User", modifier = Modifier.align(Alignment.CenterVertically))
                }
                Row {
                    RadioButton(
                        selected = accountType == "Service Provider",
                        onClick = { accountType = "Service Provider" },
                        colors = RadioButtonDefaults.colors(green2)
                    )
                    Text(text = "Service Provider", modifier = Modifier.align(Alignment.CenterVertically))
                }
                Row {
                    RadioButton(
                        selected = accountType == "Market Vendor",
                        onClick = { accountType = "Market Vendor" },
                        colors = RadioButtonDefaults.colors(green2)
                    )
                    Text(text = "Market Vendor", modifier = Modifier.align(Alignment.CenterVertically))
                }
                Row {
                    RadioButton(
                        selected = accountType == "Financial Institution",
                        onClick = { accountType = "Financial Institution" },
                        colors = RadioButtonDefaults.colors(green2)
                    )
                    Text(text = "Financial Institution", modifier = Modifier.align(Alignment.CenterVertically))
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (accountType == "Service Provider") {
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
                            value = serviceProviderName,
                            onValueChange = { serviceProviderName = it },
                            placeholder = { Text("Service Provider Name", color = Color.Gray) },
                            textStyle = TextStyle(color = Color.Black),
                            singleLine = true,
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent,
                                focusedIndicatorColor = green2,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = green2
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 0.dp)
                        )
                    }
                }
            }

        }

        if (accountType == "Market Vendor") {
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
                            value = facilityName,
                            onValueChange = { facilityName = it },
                            placeholder = { Text("Facility Name", color = Color.Gray) },
                            textStyle = TextStyle(color = Color.Black),
                            singleLine = true,
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent,
                                focusedIndicatorColor = green2,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = green2
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 0.dp)
                        )
                    }
                }
            }


        }

        if (accountType == "Financial Institution") {
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
                            value = agencyName,
                            onValueChange = { agencyName = it },
                            placeholder = { Text("Agency Name", color = Color.Gray) },
                            singleLine = true,
                            textStyle = TextStyle(color = Color.Black),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent,
                                focusedIndicatorColor = green2,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = green2
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 0.dp)
                        )
                    }
                }
            }


        }

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
                        value = location,
                        onValueChange = { location = it },
                        placeholder = { Text("Location", color = Color.Gray) },
                        singleLine = true,
                        textStyle = TextStyle(color = Color.Black),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = green2,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = green2
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 0.dp)
                    )
                }
            }
        }



        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Surface(
                color = Color.LightGray,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .height(100.dp)
                    .padding(horizontal = 6.dp)
                    .padding(vertical = 5.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = bio,
                        onValueChange = { bio = it },
                        placeholder = { Text("Bio", color = Color.Gray) },
                        textStyle = TextStyle(color = Color.Black),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = green2,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = green2
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 0.dp)
                    )
                }
            }
        }



        Spacer(modifier = Modifier.height(12.dp))

        // Sign Up Button
        Button(
            onClick = {
                if (password == confirmPassword) {
                    showLoadingDialog = true
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            showLoadingDialog = false
                            if (task.isSuccessful) {
                                val userId = auth.currentUser?.uid
                                Log.d("SignUp", "User created with ID: $userId")
                                val user = hashMapOf(
                                    "username" to username,
                                    "email" to email,
                                    "phoneNumber" to phoneNumber,
                                    "accountType" to accountType,
                                    "location" to location,
                                    "bio" to bio
                                )

                                when (accountType) {
                                    "Service Provider" -> user["serviceProviderName"] = serviceProviderName
                                    "Market Vendor" -> user["facilityName"] = facilityName
                                    "Financial Institution" -> user["agencyName"] = agencyName
                                }

                                userId?.let {
                                    firestore.collection("users").document(it).set(user)
                                        .addOnSuccessListener {
                                            Timber.tag("Firestore")
                                                .d("User document successfully written!")
                                            showSuccessDialog = true
                                        }
                                        .addOnFailureListener { e ->
                                            Timber.tag("Firestore")
                                                .e(e, "Error writing user document")
                                            showFailureDialog = true
                                        }
                                } ?: run {
                                Timber.tag("SignUp").e("User ID is null after successful authentication")
                                    showFailureDialog = true
                                }
                            } else {
                                signUpMessage = task.exception?.message ?: "Sign Up Failed"
                                Timber.tag("SignUp").e("Authentication failed: %s", signUpMessage)
                                showFailureDialog = true
                            }
                        }
                } else {
                    signUpMessage = "Passwords do not match"
                    Timber.tag("SignUp").e("Passwords do not match")
                    showFailureDialog = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(shape = RoundedCornerShape(10.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = green2
            )
        ) {
            Text(text = "Sign Up", color = white, fontSize = 16.sp)
        }



        if (showLoadingDialog) {
            AlertDialog(
                onDismissRequest = { },
                title = { Text(text = "Signing Up...") },
                text = { CircularProgressIndicator(color = green2) },
                confirmButton = { }
            )
        }

        if (showSuccessDialog) {
            AlertDialog(
                onDismissRequest = {
                    showSuccessDialog = false
                    navController.navigate(Screens.Login.route)
                },
                title = { Text(text = "Success") },
                text = { Text(text = "You have successfully signed up!") },
                confirmButton = {
                    Button(onClick = {
                        showSuccessDialog = false
                        navController.navigate(Screens.Login.route)
                    }) {
                        Text(text = "OK")
                    }
                }
            )
        }

        if (showFailureDialog) {
            AlertDialog(
                onDismissRequest = { showFailureDialog = false },
                title = { Text(text = "Error") },
                text = { Text(text = signUpMessage) },
                confirmButton = {
                    Button(onClick = { showFailureDialog = false }) {
                        Text(text = "OK")
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        ClickableText(
            text = AnnotatedString("Already have an account? Log in"),
            style = TextStyle(color = green2),
            onClick = {
                navController.navigate(Screens.Login.route)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
    val navController = rememberNavController()
    SignupScreen(navController) { _, _, _, _ -> }
}
