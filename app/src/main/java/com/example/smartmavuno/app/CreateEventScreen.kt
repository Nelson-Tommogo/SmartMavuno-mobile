package com.example.smartmavuno.app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartmavuno.R
import com.example.smartmavuno.ui.theme.black
import com.example.smartmavuno.ui.theme.green1
import com.example.smartmavuno.viewmodel.CalendarViewModel
import com.example.smartmavuno.viewmodel.Event
import java.time.LocalDate
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateEventScreen(navController: NavController, viewModel: CalendarViewModel = viewModel()) {
    var eventName by remember { mutableStateOf("") }
    var eventDate by remember { mutableStateOf("") }
    var eventTime by remember { mutableStateOf("") }
    val repeatEvent by remember { mutableStateOf(false) }
    var allDayEvent by remember { mutableStateOf(false) }
    var weekdaysEvent by remember { mutableStateOf(false) }
    var weekendsEvent by remember { mutableStateOf(false) }
    var notificationOption by remember { mutableStateOf("None") }
    var location by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var showNotificationDialog by remember { mutableStateOf(false) }
    var showLocationDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
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
                text = "Add Event",
                style = MaterialTheme.typography.titleLarge,
                color = black,
                modifier = Modifier.align(Alignment.Center)
            )
            Row(
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Button(
                    onClick = {
                        if (eventDate.isNotEmpty() && eventTime.isNotEmpty()) {
                            val parsedDate = LocalDate.parse(eventDate)
                            val parsedTime = LocalTime.parse(eventTime)
                            viewModel.addEvent(Event(parsedDate.atTime(parsedTime), eventName, repeatEvent))
                            navController.popBackStack()
                        } else {
                            // Handle validation errors (e.g., show a toast or snackbar)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = green1),
                ) {
                    Text("Save")
                }
            }
        }

        TextField(
            value = eventName,
            onValueChange = { eventName = it },
            placeholder = { Text("Event Name", color = black) },
            textStyle = TextStyle(color = green1),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = green1,
                containerColor = Color.LightGray,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            visualTransformation = VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        TextField(
            value = eventDate,
            onValueChange = { eventDate = it },
            placeholder = { Text("Event Date (YYYY-MM-DD)", color = black) },
            textStyle = TextStyle(color = green1),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = green1,
                containerColor = Color.LightGray,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            visualTransformation = VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        TextField(
            value = eventTime,
            onValueChange = { eventTime = it },
            placeholder = { Text("Event Time (HH:MM)", color = black) },
            textStyle = TextStyle(color = green1),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = green1,
                containerColor = Color.LightGray,
                unfocusedIndicatorColor = Color.LightGray,
                focusedIndicatorColor = Color.Transparent
            ),
            visualTransformation = VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Checkbox(
                checked = allDayEvent,
                onCheckedChange = {
                    allDayEvent = it
                    if (it) {
                        weekdaysEvent = false
                        weekendsEvent = false
                    }
                }
            )
            Text("All Day Event", color = green1)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Checkbox(
                checked = weekdaysEvent,
                onCheckedChange = {
                    weekdaysEvent = it
                    if (it) {
                        allDayEvent = false
                        weekendsEvent = false
                    }
                }
            )
            Text("On Weekdays", color = green1)
            Spacer(modifier = Modifier.width(16.dp))
            if (weekdaysEvent) {
                TextField(
                    value = eventTime,
                    onValueChange = { eventTime = it },
                    placeholder = { Text("Event Time (HH:MM)", color = black) },
                    textStyle = TextStyle(color = green1),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = green1,
                        containerColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    visualTransformation = VisualTransformation.None,
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp)
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Checkbox(
                checked = weekendsEvent,
                onCheckedChange = {
                    weekendsEvent = it
                    if (it) {
                        allDayEvent = false
                        weekdaysEvent = false
                    }
                }
            )
            Text("On Weekends", color = green1)
            Spacer(modifier = Modifier.width(16.dp))
            if (weekendsEvent) {
                TextField(
                    value = eventTime,
                    onValueChange = { eventTime = it },
                    placeholder = { Text("Event Time (HH:MM)", color = black) },
                    textStyle = TextStyle(color = green1),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = green1,
                        containerColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    visualTransformation = VisualTransformation.None,
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp)
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { showNotificationDialog = true }
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notification",
                tint = green1
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Notification: $notificationOption", color = green1)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { showLocationDialog = true }
        ) {
            Icon(
                imageVector = Icons.Default.Place,
                contentDescription = "Location",
                tint = green1
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Location: $location", color = green1)
        }

        TextField(
            value = description,
            onValueChange = { description = it },
            placeholder = { Text("Description", color = black) },
            textStyle = TextStyle(color = green1),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = green1,
                containerColor = Color.LightGray,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            visualTransformation = VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable {
                    // Implement event attachment logic
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_attach_file_24),
                contentDescription = "Attachment",
                tint = green1
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Add Attachment", color = green1)
        }
    }

    if (showNotificationDialog) {
        NotificationDialog(
            onDismiss = { showNotificationDialog = false },
            onOptionSelected = { option ->
                notificationOption = option
                showNotificationDialog = false
            }
        )
    }

    if (showLocationDialog) {
        LocationDialog(
            onDismiss = { showLocationDialog = false },
            onLocationEntered = { enteredLocation ->
                location = enteredLocation
                showLocationDialog = false
            }
        )
    }
}

@Composable
fun NotificationDialog(
    onDismiss: () -> Unit,
    onOptionSelected: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        text = {
            Column {
                Text("Select Notification Time")
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { onOptionSelected("30 mins") }) {
                    Text("30 mins")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { onOptionSelected("45 mins") }) {
                    Text("45 mins")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { onOptionSelected("1 hour") }) {
                    Text("1 hour")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { onOptionSelected("1 day") }) {
                    Text("1 day")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { onOptionSelected("1 week") }) {
                    Text("1 week")
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDialog(
    onDismiss: () -> Unit,
    onLocationEntered: (String) -> Unit
) {
    var enteredLocation by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = { onLocationEntered(enteredLocation) }) {
                Text("OK")
            }
        },
        text = {
            Column {
                Text("Enter Location")
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = enteredLocation,
                    onValueChange = { enteredLocation = it },
                    placeholder = { Text("Location", color = black) },
                    textStyle = TextStyle(color = green1),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = green1,
                        containerColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    visualTransformation = VisualTransformation.None,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun CreateEventScreenPreview() {
    val navController = rememberNavController()
    CreateEventScreen(navController = navController)
}
