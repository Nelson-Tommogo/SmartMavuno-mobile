package com.example.smartmavuno.app

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import com.example.smartmavuno.ui.theme.black
import com.example.smartmavuno.ui.theme.green1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandOwner(
    navController: NavController,
    onImageOrVideoCaptured: String,
    onSubmit: String
) {
    var landDescription by remember { mutableStateOf("") }
    var landSize by remember { mutableStateOf("Acres") }
    var location by remember { mutableStateOf("") }
    var currentUse by remember { mutableStateOf("") }
    var leaseDuration by remember { mutableStateOf("1 Year") }
    var leasePayment by remember { mutableStateOf("Per Year") }
    var additionalConditions by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Lessor - Lease Your Land", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = landDescription,
            onValueChange = { landDescription = it },
            placeholder = { Text("Land Description", color = black) },
            textStyle = TextStyle(color = green1),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = green1,
                containerColor = Color.LightGray,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            visualTransformation = VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Land Size Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(Color.LightGray, RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Column {
                Text("Select Land Size", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                LandSizeOptions(selectedOption = landSize) { selectedOption ->
                    landSize = selectedOption
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Land Size Input
        TextField(
            value = price,
            onValueChange = { price = it },
            placeholder = { Text("Enter Size", color = black) },
            textStyle = TextStyle(color = green1),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = green1,
                containerColor = Color.LightGray,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            visualTransformation = VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Location TextField
        TextField(
            value = location,
            onValueChange = { location = it },
            placeholder = { Text("Location (address or GPS coordinates)", color = black) },
            textStyle = TextStyle(color = green1),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = green1,
                containerColor = Color.LightGray,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            visualTransformation = VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Current Use of Land TextField
        TextField(
            value = currentUse,
            onValueChange = { currentUse = it },
            placeholder = { Text("Current Use of Land", color = black) },
            textStyle = TextStyle(color = green1),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = green1,
                containerColor = Color.LightGray,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            visualTransformation = VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Lease Duration Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(Color.LightGray, RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Column {
                Text("Select Lease Duration", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                LeaseDurationOptions(selectedOption = leaseDuration) { selectedOption ->
                    leaseDuration = selectedOption
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lease Payment Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(Color.LightGray, RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Column {
                Text("Select Lease Payment", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                LeasePaymentOptions(selectedOption = leasePayment) { selectedOption ->
                    leasePayment = selectedOption
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lease Cost TextField
        TextField(
            value = price,
            onValueChange = { price = it },
            placeholder = { Text("Enter Land Lease Cost", color = black) },
            textStyle = TextStyle(color = green1),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = green1,
                containerColor = Color.LightGray,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            visualTransformation = VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Additional Conditions TextField
        TextField(
            value = additionalConditions,
            onValueChange = { additionalConditions = it },
            placeholder = { Text("Additional Conditions or Restrictions", color = black) },
            textStyle = TextStyle(color = green1),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = green1,
                containerColor = Color.LightGray,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            visualTransformation = VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Handle Image/Video capture
            },
            colors = ButtonDefaults.buttonColors(containerColor = green1),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Upload or Capture Land Pictures/Videos")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Handle form submission
            },
            colors = ButtonDefaults.buttonColors(containerColor = green1),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit Lease Details")
        }
    }
}

@Composable
fun LandSizeOptions(selectedOption: String, onOptionSelected: (String) -> Unit) {
    val options = listOf("Acres", "Hectares", "Square Meters", "Square Kilometers", "Square Feet")
    options.forEach { option ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onOptionSelected(option) }
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = option == selectedOption,
                onClick = { onOptionSelected(option) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(option)
        }
    }
}

@Composable
fun LeaseDurationOptions(selectedOption: String, onOptionSelected: (String) -> Unit) {
    val options = listOf("1 Year", "2 Years", "3 Years", "5 Years", "10 Years")
    options.forEach { option ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onOptionSelected(option) }
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = option == selectedOption,
                onClick = { onOptionSelected(option) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(option)
        }
    }
}

@Composable
fun LeasePaymentOptions(selectedOption: String, onOptionSelected: (String) -> Unit) {
    val options = listOf("Per Month", "Per Year", "One-time Payment")
    options.forEach { option ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onOptionSelected(option) }
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = option == selectedOption,
                onClick = { onOptionSelected(option) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(option)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LandOwnerPreview() {
    LandOwner(navController = NavController(LocalContext.current), onImageOrVideoCaptured = "", onSubmit = "")
}
