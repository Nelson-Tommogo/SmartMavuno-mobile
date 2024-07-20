package com.example.smartmavuno.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.smartmavuno.R
import com.example.smartmavuno.ui.theme.black
import com.example.smartmavuno.ui.theme.green1
import com.example.smartmavuno.ui.theme.green3

data class Bank(val name: String, val iconResId: Int)

@Composable
fun PaymentOptions(navController: NavHostController) {
    val green1 = colorResource(id = R.color.green1)
    val coroutineScope = rememberCoroutineScope()
    var donationAmount by remember { mutableStateOf("") }
    var selectedBank by remember { mutableStateOf<Bank?>(null) }
    var showBankDropdown by remember { mutableStateOf(false) }

    val banks = listOf(
        Bank("Equity Bank", R.drawable.bank),
        Bank("KCB Bank", R.drawable.bank),
        Bank("NCBA Bank", R.drawable.bank),
        Bank("Cooperative Bank", R.drawable.bank),
        Bank("Stanbic Bank", R.drawable.bank),
        Bank("Barclays Bank", R.drawable.bank),
        Bank("Family Bank", R.drawable.bank),
        Bank("Diamond Trust Bank", R.drawable.bank),
        Bank("CBA Bank", R.drawable.bank),
        Bank("HF Bank", R.drawable.bank)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(green1)
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable { navController.popBackStack() }
            )
            Text(
                text = "Select Donation Method",
                style = MaterialTheme.typography.titleLarge,
                color = black,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        PaymentOptionCard(
            iconResId = R.drawable.paypal,
            title = "PayPal",
            description = "Donate using PayPal",
            onClick = { navController.navigate("paypal_screen/$donationAmount") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        PaymentOptionCard(
            iconResId = R.drawable.bank,
            title = "Bank Transfer",
            description = "Donate via Bank Transfer",
            onClick = { showBankDropdown = !showBankDropdown }
        )

        Spacer(modifier = Modifier.height(8.dp))

        PaymentOptionCard(
            iconResId = R.drawable.mpesa,
            title = "M-Pesa",
            description = "Donate using M-Pesa",
            onClick = { navController.navigate("mpesa_screen/$donationAmount") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        DonateProductForm(
            donationAmount = donationAmount,
            onAmountChange = { donationAmount = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (showBankDropdown) {
            Text(text = "Select Your Bank", style = MaterialTheme.typography.titleMedium, color = black)

            Spacer(modifier = Modifier.height(8.dp))

            BankDropdown(banks = banks, selectedBank = selectedBank, onBankSelected = { selectedBank = it })
        }
    }
}

@Composable
fun PaymentOptionCard(
    iconResId: Int,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = title,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                contentDescription = "Forward",
                modifier = Modifier.size(24.dp),
                tint = black
            )
        }
    }
}

@Composable
fun DonateProductForm(
    donationAmount: String,
    onAmountChange: (String) -> Unit
) {
    val green3 = colorResource(id = R.color.green3)

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .background(green3)
                .padding(16.dp)
        ) {
            Text(
                text = "Donate Products",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = donationAmount,
                onValueChange = onAmountChange,
                label = { Text("Donation Amount") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Product Details") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Pick-up Points") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Handle the button click here
                },
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(
                    containerColor = green1 // Custom button color
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text(
                    text = "Submit Donation \uD83D\uDCB3",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color.White
                    )
                )
            }
        }
    }
}

@Composable
fun BankDropdown(banks: List<Bank>, selectedBank: Bank?, onBankSelected: (Bank) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedBank?.name ?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text("Select Bank") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            trailingIcon = {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Dropdown")
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            banks.forEach { bank ->
                DropdownMenuItem(
                    onClick = {
                        onBankSelected(bank)
                        expanded = false
                    }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = bank.iconResId),
                            contentDescription = bank.name,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = bank.name)
                    }
                }
            }
        }
    }
}

fun DropdownMenuItem(onClick: () -> Unit, interactionSource: @Composable () -> Unit) {
    TODO("Not yet implemented")
}

@Composable
fun PaypalScreen(donationAmount: String) {
    // Implement PayPal screen here
}

@Composable
fun BankTransferScreen(donationAmount: String) {
    // Implement Bank Transfer screen here
}

@Composable
fun MpesaScreen(donationAmount: String) {
    // Implement M-Pesa screen here
}

@Preview(showBackground = true)
@Composable
fun PreviewPaymentOptions() {
    PaymentOptions(navController = rememberNavController())
}
