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

@Composable
fun LandOwner(
    navController: NavController,
    onImageOrVideoCaptured: String,
    onSubmit: String
) {
    val landDescription = remember { mutableStateOf("") }
    val landSize = remember { mutableStateOf("Acres") }
    val location = remember { mutableStateOf("") }
    val currentUse = remember { mutableStateOf("") }
    val leaseDuration = remember { mutableStateOf("1 Year") }
    val leasePayment = remember { mutableStateOf("Per Year") }
    val additionalConditions = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Lessor - Lease Your Land", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            label = { Text("Land Description") },
            value = landDescription.value,
            onValueChange = { landDescription.value = it },
            modifier = Modifier.fillMaxWidth()
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
                LandSizeOptions(selectedOption = landSize.value) { selectedOption ->
                    landSize.value = selectedOption
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            label = { Text("Enter Size") },
            value = additionalConditions.value,
            onValueChange = { additionalConditions.value = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            label = { Text("Location (address or GPS coordinates)") },
            value = location.value,
            onValueChange = { location.value = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            label = { Text("Current Use of Land") },
            value = currentUse.value,
            onValueChange = { currentUse.value = it },
            modifier = Modifier.fillMaxWidth()
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
                LeaseDurationOptions(selectedOption = leaseDuration.value) { selectedOption ->
                    leaseDuration.value = selectedOption
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
                LeasePaymentOptions(selectedOption = leasePayment.value) { selectedOption ->
                    leasePayment.value = selectedOption
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            label = { Text("Enter Land Price") },
            value = additionalConditions.value,
            onValueChange = { additionalConditions.value = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            label = { Text("Additional Conditions or Restrictions") },
            value = additionalConditions.value,
            onValueChange = { additionalConditions.value = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Handle image or video capture logic
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Upload or Capture Land Pictures/Videos")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { onSubmit }, modifier = Modifier.fillMaxWidth()) {
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
    val options = listOf("Per Year", "Per Quarter", "Per Month", "Per Acre", "Per Hectare")
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


fun onImageOrVideoCaptured(toString: String) {
    TODO("Not yet implemented")
}

@Composable
fun CustomDialog(
    dialogType: String,
    selectedOption: String,
    onDismiss: () -> Unit,
    onOptionSelected: (String) -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = when (dialogType) {
                        "Land Size" -> "Select Land Size"
                        "Lease Duration" -> "Select Lease Duration"
                        "Lease Payment" -> "Select Lease Payment"
                        "Image/Video" -> "Choose an Option"
                        else -> ""
                    },
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.LightGray)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        when (dialogType) {
                            "Land Size" -> {
                                RadioButtonOption("Acres", selectedOption, onOptionSelected)
                                RadioButtonOption("Hectares", selectedOption, onOptionSelected)
                                RadioButtonOption("Square Meters", selectedOption, onOptionSelected)
                                RadioButtonOption("Square Kilometers", selectedOption, onOptionSelected)
                                RadioButtonOption("Square Feet", selectedOption, onOptionSelected)
                            }
                            "Lease Duration" -> {
                                RadioButtonOption("1 Year", selectedOption, onOptionSelected)
                                RadioButtonOption("2 Years", selectedOption, onOptionSelected)
                                RadioButtonOption("3 Years", selectedOption, onOptionSelected)
                                RadioButtonOption("5 Years", selectedOption, onOptionSelected)
                                RadioButtonOption("10 Years", selectedOption, onOptionSelected)
                            }
                            "Lease Payment" -> {
                                RadioButtonOption("Per Year", selectedOption, onOptionSelected)
                                RadioButtonOption("Per Quarter", selectedOption, onOptionSelected)
                                RadioButtonOption("Per Month", selectedOption, onOptionSelected)
                                RadioButtonOption("Per Acre", selectedOption, onOptionSelected)
                                RadioButtonOption("Per Hectare", selectedOption, onOptionSelected)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { onDismiss() },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Done")
                }
            }
        }
    }
}

@Composable
fun RadioButtonOption(
    text: String,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onOptionSelected(text) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = text == selectedOption,
            onClick = { onOptionSelected(text) }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}


@Preview(showBackground = true)
@Composable
fun LandOwnerPreview() {
    // Replace this with actual NavController in a real implementation
    LandOwner(
        navController = NavController(LocalContext.current),
        onImageOrVideoCaptured = {}.toString(),
        onSubmit = {}.toString()
    )
}
