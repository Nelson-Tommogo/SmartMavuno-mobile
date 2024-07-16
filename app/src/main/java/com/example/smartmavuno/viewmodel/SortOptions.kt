package com.example.smartmavuno.viewmodel


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smartmavuno.viewmodel.SortBy

@Composable
fun SortOptions(
    sortBy: SortBy,
    onSortChange: (SortBy) -> Unit
) {
    Row(modifier = Modifier.padding(16.dp)) {
        Text(text = "Sort by:", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.width(8.dp))
        SortOptionButton(text = "Ratings", selected = sortBy == SortBy.Ratings) {
            onSortChange(SortBy.Ratings)
        }
        Spacer(modifier = Modifier.width(8.dp))
        SortOptionButton(text = "Price", selected = sortBy == SortBy.Price) {
            onSortChange(SortBy.Price)
        }
        Spacer(modifier = Modifier.width(8.dp))
        SortOptionButton(text = "Frequently Ordered", selected = sortBy == SortBy.FrequentlyOrdered) {
            onSortChange(SortBy.FrequentlyOrdered)
        }
    }
}

@Composable
fun SortOptionButton(text: String, selected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            contentColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
        )
    ) {
        Text(text = text)
    }
}
