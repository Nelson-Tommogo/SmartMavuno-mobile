package com.example.smartmavuno.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartmavuno.R
import com.example.smartmavuno.ui.theme.green1
import com.example.smartmavuno.ui.theme.green3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(navController: NavController) {
    var location by remember { mutableStateOf("Nairobi") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        // Location Input
        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Enter location") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = green1,
                unfocusedBorderColor = Color.Gray,
                cursorColor = green1
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Get Weather Button
        Button(
            onClick = { /* Implement get weather functionality */ },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = green1
            )
        ) {
            Text("Get Weather")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Current Weather Section
        CurrentWeatherSection()

        Spacer(modifier = Modifier.height(16.dp))

        // Forecast Section
        ForecastSection()

        Spacer(modifier = Modifier.height(16.dp))

        // Weekly Forecast Section
        WeeklyForecastSection()
    }
}

@Composable
fun CurrentWeatherSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Current Weather", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "25°C", fontSize = 48.sp, fontWeight = FontWeight.Bold)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.clickable { /* Implement current weather click functionality */ }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_sunny_24),
                    contentDescription = "Sunny",
                    modifier = Modifier.size(24.dp)

                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Sunny", fontSize = 20.sp)
            }
        }
    }
}

@Composable
fun ForecastSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Today's Forecast", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            // Add forecast items
            ForecastItem(hour = "10 AM", temperature = "23°C", weather = "Sunny", R.drawable.baseline_sunny_24)
            ForecastItem(hour = "1 PM", temperature = "25°C", weather = "Partly Cloudy", R.drawable.baseline_cloud_24)
            ForecastItem(hour = "4 PM", temperature = "24°C", weather = "Cloudy", R.drawable.baseline_cloudy_snowing_24)
        }
    }
}

@Composable
fun ForecastItem(hour: String, temperature: String, weather: String, drawableId: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { /* Implement forecast item click functionality */ },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = hour, fontSize = 16.sp)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Image(
                painter = painterResource(id = drawableId),
                contentDescription = weather,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = temperature, fontSize = 16.sp)
        }
        Text(text = weather, fontSize = 16.sp)
    }
}

@Composable
fun WeeklyForecastSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Weekly Forecast", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            // Add weekly forecast items
            WeeklyForecastItem(day = "Monday", temperature = "24°C", weather = "Sunny", R.drawable.baseline_sunny_24)
            WeeklyForecastItem(day = "Tuesday", temperature = "22°C", weather = "Cloudy", R.drawable.baseline_cloud_24)
            WeeklyForecastItem(day = "Wednesday", temperature = "23°C", weather = "Rainy", R.drawable.baseline_cloudy_snowing_24)
            WeeklyForecastItem(day = "Thursday", temperature = "23°C", weather = "Rainy", R.drawable.baseline_cloudy_snowing_24)
            WeeklyForecastItem(day = "Friday", temperature = "23°C", weather = "Rainy", R.drawable.baseline_cloudy_snowing_24)
            WeeklyForecastItem(day = "Saturday", temperature = "23°C", weather = "Rainy", R.drawable.baseline_cloudy_snowing_24)
            WeeklyForecastItem(day = "Sunday", temperature = "23°C", weather = "Rainy", R.drawable.baseline_cloudy_snowing_24)
        }
    }
}

@Composable
fun WeeklyForecastItem(day: String, temperature: String, weather: String, drawableId: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { /* Implement weekly forecast item click functionality */ },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = day, fontSize = 16.sp)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Image(
                painter = painterResource(id = drawableId),
                contentDescription = weather,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = temperature, fontSize = 16.sp)
        }
        Text(text = weather, fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWeatherScreen() {
    WeatherScreen(navController = NavController(context = LocalContext.current))
}
