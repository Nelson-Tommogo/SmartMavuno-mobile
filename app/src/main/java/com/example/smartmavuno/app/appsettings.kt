package com.example.smartmavuno.app

import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartmavuno.R

data class SettingsItem(
    val title: String,
    val startIcon: Int,
    val endIcon: Int,
    val onClick: () -> Unit
)

@Composable
fun SettingsScreen(navController: NavController) {
    var selectedLanguage by remember { mutableStateOf("English") }
    var isLanguageSelectionVisible by remember { mutableStateOf(false) }
    var isDarkMode by remember { mutableStateOf(false) }

    val languages = listOf("English", "Swahili", "French", "Zulu", "Afrikaans")

    val settingsItems = remember {
        mutableStateListOf(
            SettingsItem("Personal Information", R.drawable.info, R.drawable.navigatemore) { /* Implement navigation or action */ },
            SettingsItem("Notification", R.drawable.notificationme, R.drawable.navigatemore) { /* Implement navigation or action */ },
            SettingsItem("Security", R.drawable.security, R.drawable.navigatemore) { /* Implement navigation or action */ },
            SettingsItem("Language", R.drawable.language, R.drawable.navigatemore) { isLanguageSelectionVisible = true },
            SettingsItem("Dark Mode/Light Mode", R.drawable.darkmode, R.drawable.lightmode) { isDarkMode = !isDarkMode },
            SettingsItem("Help Centre", R.drawable.help, R.drawable.navigatemore) { /* Implement navigation or action */ },
            SettingsItem("Log out", R.drawable.logout, R.drawable.navigatemore) { /* Implement logout */ }
        )
    }

    val rotationAngle by animateFloatAsState(if (isDarkMode) 180f else 0f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Top bar with back icon and title
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.backhome),
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.popBackStack() },
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Settings", style = MaterialTheme.typography.titleMedium)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // List of settings items
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(settingsItems) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { item.onClick() },
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = item.startIcon),
                            contentDescription = item.title,
                            modifier = Modifier.size(24.dp),
                            tint = Color.Unspecified
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = item.title, style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.weight(1f))
                        if (item.title == "Dark Mode/Light Mode") {
                            Icon(
                                painter = painterResource(id = item.endIcon),
                                contentDescription = "Light Mode",
                                modifier = Modifier
                                    .size(24.dp)
                                    .graphicsLayer {
                                        rotationZ = rotationAngle
                                    }
                                    .clickable { item.onClick() },
                                tint = Color.Unspecified
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = item.endIcon),
                                contentDescription = "Arrow",
                                modifier = Modifier.size(24.dp),
                                tint = Color.Unspecified
                            )
                        }
                    }
                }
            }

            // Language selection logic
            if (isLanguageSelectionVisible) {
                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Select Language", style = MaterialTheme.typography.bodyMedium)
                        DropdownMenu(
                            expanded = true,
                            onDismissRequest = { isLanguageSelectionVisible = false }
                        ) {
                            languages.forEach { language ->
                                DropdownMenuItem(
                                    text = {
                                        Text(text = language)
                                    },
                                    onClick = {
                                        selectedLanguage = language
                                        isLanguageSelectionVisible = false
                                        // Implement language change logic here
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(navController = rememberNavController())
}
