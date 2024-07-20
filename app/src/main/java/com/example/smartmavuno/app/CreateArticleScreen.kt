package com.example.smartmavuno.app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartmavuno.R
import com.example.smartmavuno.ui.theme.green1
import com.example.smartmavuno.ui.theme.green3
import com.example.smartmavuno.ui.theme.grey
import com.example.smartmavuno.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleCreationScreen(
    navController: NavController,
    onSave: () -> Unit
) {
    val coverPhoto = remember { mutableStateOf<Painter?>(null) }
    val title = remember { mutableStateOf("") }
    val content = remember { mutableStateOf("") }
    val isFileAttached = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
            .padding(16.dp)
    ) {
        // Top Row with Cancel Icon and Text
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_cancel_24),
                    contentDescription = "Cancel",
                    tint = green1,
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(
                text = "Create Articles",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black
            )
            Spacer(modifier = Modifier.size(24.dp))
        }

        // Cover Photo Upload
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(grey)
                .clickable {
                    // Handle cover photo upload
                },
            contentAlignment = Alignment.Center
        ) {
            if (coverPhoto.value == null) {
                Text(
                    text = "Upload Cover Photo",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                Icon(
                    painter = coverPhoto.value!!,
                    contentDescription = "Cover Photo",
                    modifier = Modifier.size(100.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Article Title
        TextField(
            value = title.value,
            onValueChange = { title.value = it },
            label = { Text("Article Title") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .background(color = Color.Transparent, shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Color.Black,
                containerColor = grey,
                unfocusedTextColor = Color.Black,
                focusedIndicatorColor = green1,
                unfocusedIndicatorColor = green3
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Article Content
        TextField(
            value = content.value,
            onValueChange = { content.value = it },
            label = { Text("Article Content") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(200.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                containerColor = grey,
                focusedIndicatorColor = green1,
                unfocusedIndicatorColor = grey
            ),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(6.dp))

        // File Attachment Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    isFileAttached.value = !isFileAttached.value
                    // Handle file attachment logic here
                },
                modifier = Modifier.padding(start = 8.dp) // Add padding to start of the IconButton
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_attach_file_24),
                    contentDescription = "Attach File",
                    tint = green1,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp)) // Space between the icon and text

            Text(
                text = "Upload Article",
                color = green1,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        // Save Button
        Spacer(modifier = Modifier.height(6.dp))
        Button(
            onClick = {
                onSave()
                //implement the save logic, or navigate somewhere
                navController.navigate("someOtherScreen")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .clip(RoundedCornerShape(8.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = green1)
        ) {
            Text(text = "Save Article", color = white)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleCreationScreenPreview() {
    val navController = rememberNavController()
    ArticleCreationScreen(
        navController = navController,
        onSave = { /* Handle save action */ }
    )
}
