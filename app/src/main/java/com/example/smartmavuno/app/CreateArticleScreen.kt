package com.example.smartmavuno.app


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.smartmavuno.R
import com.example.smartmavuno.ui.theme.green1
import com.example.smartmavuno.ui.theme.grey
import com.example.smartmavuno.ui.theme.white

@Composable
fun ArticleCreationScreen(onCancel: () -> Unit, onSave: () -> Unit) {
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
            IconButton(onClick = onCancel) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_cancel_24),
                    contentDescription = "Cancel",
                    tint = green1,
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(
                text = "Create Article",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black
            )
            // Empty space to keep alignment consistent
            Box(modifier = Modifier.size(24.dp))
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
                Text(text = "Upload Cover Photo", color = Color.Black)
            } else {
                Icon(
                    painter = coverPhoto.value!!,
                    contentDescription = "Cover Photo",
                    modifier = Modifier.size(100.dp)
                )
            }
        }

        // Article Title
        TextField(
            value = title.value,
            onValueChange = { title.value = it },
            label = { Text("Article Title") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedIndicatorColor = green1,
                unfocusedIndicatorColor = grey
            )
        )

        // Article Content
        TextField(
            value = content.value,
            onValueChange = { content.value = it },
            label = { Text("Article Content") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(200.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedIndicatorColor = green1,
                unfocusedIndicatorColor = grey
            ),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
        )

        // File Attachment Icon and Save Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                isFileAttached.value = !isFileAttached.value
                // Handle file attachment logic here
            }) {
                Icon(
                    painter = painterResource(id = if (isFileAttached.value) R.drawable.baseline_attach_file_24 else R.drawable.baseline_attachment_24),
                    contentDescription = "Attach File",
                    tint = green1,
                    modifier = Modifier.size(24.dp)
                )
            }
            Button(
                onClick = onSave,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(8.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = green1)
            ) {
                Text(text = "Save", color = white)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleCreationScreenPreview() {
    ArticleCreationScreen(
        onCancel = { /* Handle cancel action */ },
        onSave = { /* Handle save action */ }
    )
}
