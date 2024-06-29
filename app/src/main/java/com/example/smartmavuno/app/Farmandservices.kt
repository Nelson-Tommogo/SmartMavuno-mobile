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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.smartmavuno.R
import com.example.smartmavuno.ui.theme.green3

@Composable
fun FarmandServicePage() {
    val green1 = colorResource(id = R.color.green1)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(green3)
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
                    }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Farm and  Services",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
        }

        Text(
            text = "Manage Farm and Services",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )

        // com.example.smartmavuno.model.Service Boxes
        val services = listOf(
            "Machinery", "Farms",
            "Fertilizer services", "Equipment Rentals",
            "Crop Monitoring",

        )
        val serviceIcons = listOf(
            R.drawable.machinery, R.drawable.maizefarm,
            R.drawable.maizefarm, R.drawable.machinery,
            R.drawable.maizefarm,
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(services.size) { index ->
                FarmServiceBox(service = services[index], iconRes = serviceIcons[index], green1 = green1)
            }
        }
    }
}

@Composable
fun FarmServiceBox(service: String, iconRes: Int, green1: Color) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(green1)
            .clickable {
            }
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = service,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            tint = Color.Unspecified
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {

        }
        Text(
            text = service,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FarmServicePagePreview() {
    FarmandServicePage()
}
