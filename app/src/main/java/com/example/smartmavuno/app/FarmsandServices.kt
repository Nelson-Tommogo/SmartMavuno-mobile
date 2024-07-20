package com.example.smartmavuno.app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartmavuno.R
import com.example.smartmavuno.navigation.Screens
import com.example.smartmavuno.ui.theme.white

@Composable
fun FarmsandServices(navController: NavController) {
    val green1 = colorResource(id = R.color.green1)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
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
                        navController.popBackStack()
                    }
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Farm And Services",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        val services = listOf(
            "Maize Farm", "Machinery", "Farm Chemicals", "Fertilizers", "Pesticides", "Seeds", "Irrigation Systems", "Harvesting Tools"
        )
        val serviceIcons = listOf(
            R.drawable.maizefarm, R.drawable.machinery, R.drawable.machinery, R.drawable.machinery, R.drawable.machinery, R.drawable.machinery, R.drawable.irrigation, R.drawable.machinery
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
        ) {
            services.forEachIndexed { index, service ->
                val onClick: () -> Unit = when (service) {
                    "Maize Farm" -> {
                        {
                            navController.navigate(Screens.Farm.screen)
                        }
                    }
                    "Machinery" -> {
                        { /* Handle Machinery click */ }
                    }
                    "Farm Chemicals" -> {
                        { /* Handle Farm Chemicals click */ }
                    }
                    "Fertilizers" -> {
                        { /* Handle Fertilizers click */ }
                    }
                    "Pesticides" -> {
                        { /* Handle Pesticides click */ }
                    }
                    "Seeds" -> {
                        { /* Handle Seeds click */ }
                    }
                    "Irrigation Systems" -> {
                        { /* Handle Irrigation Systems click */ }
                    }
                    "Harvesting Tools" -> {
                        { /* Handle Harvesting Tools click */ }
                    }
                    else -> { {} }
                }
                FarmsandServicesBox(service = service, iconRes = serviceIcons[index], green1 = green1, onClick = onClick)
            }
        }
    }
}

@Composable
fun FarmsandServicesBox(service: String, iconRes: Int, green1: Color, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .height(200.dp)
            .width(250.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(green1)
            .clickable {
                onClick()
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = service,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = service,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                tint = Color.Unspecified
            )
            // Farm Rating (for example, 4 stars out of 5)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(4) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_star_purple500_24),
                        contentDescription = "Star",
                        tint = Color.Yellow,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.baseline_star_purple500_24),
                    contentDescription = "Star Border",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FarmsandServicesPreview() {
    FarmsandServices(navController = rememberNavController())
}
