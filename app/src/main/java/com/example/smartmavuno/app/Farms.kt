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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartmavuno.R
import com.example.smartmavuno.ui.theme.green3

@Composable
fun Farms(navController: NavController) {
    val green1 = colorResource(id = R.color.green1)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(green3)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Top Icon and Text
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
                text = "Farm Activities",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
        }

        Text(
            text = "My Farms",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )

        val services = listOf(
            "Maize Farm", "Cassava farms"
        )
        val serviceIcons = listOf(
            R.drawable.machinery, R.drawable.maizefarm
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.height(200.dp)
        ) {
            items(services.size) { index ->
                FarmsBox(service = services[index], iconRes = serviceIcons[index], green1 = green1)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(green1)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InfoBox(title = "Area", iconRes = R.drawable.farmarea)
            InfoBox(title = "Soil Score", iconRes = R.drawable.soilscore)
            InfoBox(title = "Security", iconRes = R.drawable.farmsecurity)
        }

        Spacer(modifier = Modifier.height(6.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(green1)
                .padding(16.dp)
        ) {
            Text(
                text = "Farm Photos",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                PhotoBox(title = "Front View", iconRes = R.drawable.frontview)
                PhotoBox(title = "Central View", iconRes = R.drawable.central)
                PhotoBox(title = "General View", iconRes = R.drawable.general)
            }
        }
    }
}

@Composable
fun FarmsBox(service: String, iconRes: Int, green1: Color) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(green1)
            .clickable {
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.removefarm),
                    contentDescription = "Remove com.example.smartmavuno.model.Service",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                        },
                    tint = Color.White
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_circle_outline_24),
                    contentDescription = "Add com.example.smartmavuno.model.Service",
                    modifier = Modifier
                        .size(24.dp)
                        .graphicsLayer {
                            alpha = 0.1f
                        }
                        .clickable {
                        },
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun InfoBox(title: String, iconRes: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .size(100.dp)
            .padding(8.dp)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = title,
            modifier = Modifier.size(40.dp),
            tint = Color.Unspecified
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun PhotoBox(title: String, iconRes: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .size(100.dp)
            .padding(8.dp)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = title,
            modifier = Modifier.size(60.dp),
            tint = Color.Unspecified
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FarmsPreview() {
    Farms(navController = rememberNavController())
}
