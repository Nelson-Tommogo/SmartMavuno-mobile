package com.example.smartmavuno.app

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.NavGraphBuilder

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "lessee") {
        composable("lessee") { LesseeScreen(navController) }
        composable("thank_you") { ThankYouScreen() }
    }
}

@Composable
fun LesseeScreen(navController: NavController) {
    val fullName = remember { mutableStateOf("") }
    val contactInfo = remember { mutableStateOf("") }
    val leaseDuration = remember { mutableStateOf("") }
    val proposedUse = remember { mutableStateOf("") }
    val budget = remember { mutableStateOf("") }
    val specialRequirements = remember { mutableStateOf("") }
    val idNumber = remember { mutableStateOf("") }
    val idPicture = remember { mutableStateOf("") }
    val additionalLegalDetails = remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Text("Lessee - Rent Land", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            label = { Text("Full Name") },
            value = fullName.value,
            onValueChange = { fullName.value = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            label = { Text("Contact Information (Email/Phone)") },
            value = contactInfo.value,
            onValueChange = { contactInfo.value = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            label = { Text("Preferred Lease Duration (in years)") },
            value = leaseDuration.value,
            onValueChange = { leaseDuration.value = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            label = { Text("Proposed Use of Land") },
            value = proposedUse.value,
            onValueChange = { proposedUse.value = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            label = { Text("Budget for Lease Payment") },
            value = budget.value,
            onValueChange = { budget.value = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            label = { Text("Special Requirements or Conditions") },
            value = specialRequirements.value,
            onValueChange = { specialRequirements.value = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // New fields for legal details
        OutlinedTextField(
            label = { Text("ID Number") },
            value = idNumber.value,
            onValueChange = { idNumber.value = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            label = { Text("Upload ID Picture (URL)") },
            value = idPicture.value,
            onValueChange = { idPicture.value = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            label = { Text("Additional Legal Details") },
            value = additionalLegalDetails.value,
            onValueChange = { additionalLegalDetails.value = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            // Navigate to the Thank You screen on form submission
            navController.navigate("thank_you")
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Submit Application")
        }
    }
}

@Composable
fun ThankYouScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Thank you for your application!", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Handle further actions here */ }) {
            Text("Go Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
