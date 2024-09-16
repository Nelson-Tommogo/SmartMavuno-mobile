package com.example.smartmavuno.app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartmavuno.R
import androidx.compose.runtime.*

@Composable
fun VegetableScreen() {
    // Remember the sort state
    var sortOption by remember { mutableStateOf(SortOption.POPULAR) }

    // List of vegetable items
    val items = remember {
        listOf(
            VegetableItem("Tomatoes", "Ksh 100", "4.5", R.drawable.guava),
            VegetableItem("Carrots", "Ksh 80", "4.2", R.drawable.guava),
            VegetableItem("Cabbage", "Ksh 120", "4.8", R.drawable.guava),
            // Add more vegetable items here
        )
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Top Bar
        TopBar()

        // Sort Options Buttons with sorting logic
        SortOptions(sortOption) { selectedSortOption ->
            sortOption = selectedSortOption
        }

        // Filter and Price Row
        FilterAndSortRow()

        // Sort and display the vegetable items based on the selected sort option
        val sortedItems = when (sortOption) {
            SortOption.POPULAR -> items
            SortOption.NEWEST -> items // You can sort items based on date added
            SortOption.RATING -> items.sortedByDescending { it.rating.toFloat() }
        }

        // Display the sorted vegetable items
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(sortedItems.size) { index ->
                VegetableItemBox(item = sortedItems[index])
            }
        }
    }
}

// Top Bar composable
@Composable
fun TopBar() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Vegetables",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            color = Color.Black
        )
    }
}

// Sort Options Composable with callback to handle sort selection
@Composable
fun SortOptions(
    currentSortOption: SortOption,
    onSortOptionSelected: (SortOption) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = { onSortOptionSelected(SortOption.POPULAR) }) {
            Text(
                text = "Popular",
                fontWeight = if (currentSortOption == SortOption.POPULAR) FontWeight.Bold else FontWeight.Normal
            )
        }
        Button(onClick = { onSortOptionSelected(SortOption.NEWEST) }) {
            Text(
                text = "Newest",
                fontWeight = if (currentSortOption == SortOption.NEWEST) FontWeight.Bold else FontWeight.Normal
            )
        }
        Button(onClick = { onSortOptionSelected(SortOption.RATING) }) {
            Text(
                text = "Rating",
                fontWeight = if (currentSortOption == SortOption.RATING) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}

// Filter and Price Row composable
@Composable
fun FilterAndSortRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Filter Icon and Text
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_filter_alt_24),
                contentDescription = "Filter Icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Filter", fontWeight = FontWeight.Bold)
        }

        // Price Sort Text
        Text(text = "Price: Lowest to Highest", fontWeight = FontWeight.Bold)

        // Icon at the End (could be another filter or sort)
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_drop_down_circle_24),
            contentDescription = "Price Sort Icon",
            modifier = Modifier.size(24.dp)
        )
    }
}

// Data class for Vegetable items
data class VegetableItem(
    val name: String,
    val price: String,
    val rating: String,
    val imageRes: Int
)

// Enum class for sorting options
enum class SortOption {
    POPULAR, NEWEST, RATING
}

// Composable for displaying each vegetable item
@Composable
fun VegetableItemBox(item: VegetableItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray, RoundedCornerShape(8.dp))
            .clickable { /* Handle Item Click */ }
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Item Image
            Icon(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Item Details
            Column {
                Text(text = item.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = item.price, fontSize = 16.sp)
                Text(text = "Rating: ${item.rating}", fontSize = 14.sp, color = Color.Gray)
            }

            // Like Icon at the end
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_drop_down_circle_24),
                contentDescription = "Like Icon",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewVegetableScreen() {
    VegetableScreen()
}
