package com.example.smartmavuno.app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartmavuno.R
import com.example.smartmavuno.ui.theme.white

@Composable
fun Service() {
    val grey = colorResource(id = R.color.grey)
    val green1 = colorResource(id = R.color.green1)
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Home Icon",
                tint = green1,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        // Handle back click here
                    }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Services",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
        }
        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = "Search Icon"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Text(
            text = "SmartMavuno Services",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )

        val services = listOf(
            "Land Leasing", "Agricultural Consultancies",
            "Farm Labour", "Equipment Rental",
            "Crop Monitoring", "Irrigation Services",
            "Soil Testing", "Pest Control"
        )
        val serviceIcons = listOf(
            R.drawable.landleasing, R.drawable.consultancy,
            R.drawable.farmlabour, R.drawable.equiprental,
            R.drawable.moniitor, R.drawable.irrigation,
            R.drawable.soiltesting, R.drawable.pestcontrol
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(services.size) { index ->
                ServiceBox(
                    service = services[index],
                    iconRes = serviceIcons[index],
                    green1 = green1,
                    onClick = {
                        // Handle service box click here
                        when (services[index]) {
                            "Land Leasing" -> { /* Handle Land Leasing click */ }
                            "Agricultural Consultancies" -> { /* Handle Agricultural Consultancies click */ }
                            "Farm Labour" -> { /* Handle Farm Labour click */ }
                            "Equipment Rental" -> { /* Handle Equipment Rental click */ }
                            "Crop Monitoring" -> { /* Handle Crop Monitoring click */ }
                            "Irrigation Services" -> { /* Handle Irrigation Services click */ }
                            "Soil Testing" -> { /* Handle Soil Testing click */ }
                            "Pest Control" -> { /* Handle Pest Control click */ }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ServiceBox(service: String, iconRes: Int, green1: Color, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(green1)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = service,
                    modifier = Modifier.size(80.dp),
                    tint = Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = service,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ServicePagePreview() {
    Service()
}
