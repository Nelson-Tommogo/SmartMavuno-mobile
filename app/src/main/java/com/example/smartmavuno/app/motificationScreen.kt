package com.example.smartmavuno.app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartmavuno.R

data class NotificationItem(val title: String, val message: String, val isSystem: Boolean)

@Composable
fun NotificationScreen(navController: NavController) {
    val notifications = remember {
        mutableStateListOf(
            NotificationItem("General Update", "SmartMavuno Works well now", false),
            NotificationItem("System Alert", "This is a system notification", true),
            NotificationItem("General Info", "Hello There", false),
            NotificationItem("System Maintenance", "System will be down for maintenance", true)
        )
    }

    var showGeneral by remember { mutableStateOf(true) }
    val green1 = colorResource(id = R.color.green1)
    val white = colorResource(id = R.color.white)
    val red = colorResource(id = R.color.red)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
            .padding(16.dp)
    ) {
        // Top bar with back icon, title, and clear icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.backhome),
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Notifications", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.clear),
                contentDescription = "Clear",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { notifications.clear() }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Toggle text for General and System notifications
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "General",
                    color = if (showGeneral) Color.Black else Color.Gray,
                    modifier = Modifier.clickable { showGeneral = true }
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "System",
                    color = if (!showGeneral) Color.Black else Color.Gray,
                    modifier = Modifier.clickable { showGeneral = false }
                )
            }

            // Slider to indicate current section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
                    .height(4.dp)
                    .background(Color.LightGray)
            ) {
                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .height(4.dp)
                        .align(if (showGeneral) Alignment.CenterStart else Alignment.CenterEnd)
                        .background(Color.Black)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // List of notifications
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(notifications.filter { it.isSystem != showGeneral }) { notification ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (notification.isSystem) red else green1
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = notification.title, style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = notification.message, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationScreenPreview() {
    NotificationScreen(navController = rememberNavController())
}
