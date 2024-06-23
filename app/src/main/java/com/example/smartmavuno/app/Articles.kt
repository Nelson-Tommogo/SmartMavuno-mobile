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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.smartmavuno.ui.theme.grey
import com.example.smartmavuno.ui.theme.white

@Composable
fun Articles() {
    val green1 = colorResource(id = R.color.green1)
    val green3 = colorResource(id = R.color.green3)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
            .padding(16.dp)
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
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Icon",
                tint = green1,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                    }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Articles",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(grey)
                .height(200.dp)
                .clickable {
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Trending",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
            Icon(
                painter = painterResource(id = R.drawable.baseline_bookmark_border_24),
                contentDescription = "Trending Article",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp),
                tint = Color.LightGray
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recent",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                modifier = Modifier.padding(end = 150.dp)
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Forward",
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
        }

        // Recent Articles
        val services = listOf(
            "Recent 1",
            "Recent 2", "Recent 3",
            "Recent 4", "Recent 5"
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(services.size) { index ->
                MyArticles(service = services[index], green1 = green1)
            }
        }
    }
}

@Composable
fun MyArticles(service: String, green1: Color) {
    val isBookmarked = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(8.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(grey)
            .clickable {
                // Handle service box click
            }
    ) {
        Icon(
            painter = if (isBookmarked.value) painterResource(id = R.drawable.baseline_bookmark_24) else painterResource(id = R.drawable.baseline_bookmark_border_24),
            contentDescription = service,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .clickable {
                    isBookmarked.value = !isBookmarked.value
                },
            tint = Color.Black
        )
        Text(
            text = service,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArticlesPreview() {
    Articles()
}
