package com.example.smartmavuno.appActivities


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NotificationButtons() {
    val context = LocalContext.current

    SideEffect {
        createNotificationChannels(context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            showGeneralNotification(context, "General Update", "This is a general notification")
        }) {
            Text("Send General Notification")
        }

        Button(onClick = {
            showSystemNotification(context, "System Alert", "This is a system notification")
        }) {
            Text("Send System Notification")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationButtonsPreview() {
    NotificationButtons()
}
