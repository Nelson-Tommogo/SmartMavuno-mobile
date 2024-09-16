package com.example.smartmavuno.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartmavuno.R
import kotlinx.coroutines.delay

@Composable
fun MarketplaceScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val categories = listOf(
        "Vegetables", "Fruits", "Cereals & Grains", "Poultry", "Spices", "Dairy Products","Farm Inputs"
    )
    val filteredCategories = categories.filter {
        it.contains(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        MarketplaceTopBar(navController = navController, searchQuery) {
            searchQuery = it
        }

        Spacer(modifier = Modifier.height(16.dp))

        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            AdBanner()

            Spacer(modifier = Modifier.height(16.dp))

            // Category Boxes
            if (filteredCategories.isEmpty() && searchQuery.isNotEmpty()) {
                Text(
                    text = "Not Found",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            } else {
                CategoryList(filteredCategories.ifEmpty { categories }, navController)
            }
        }
    }
}


@Composable
fun MarketplaceTopBar(navController: NavController, searchQuery: String, onSearch: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back Button
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
        }

        // Title
        Text(
            text = "Categories",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )

        IconButton(onClick = {

        }) {
            Icon(Icons.Default.Search, contentDescription = "Search")
        }
    }
}

@Composable
fun AdBanner() {
    val cropImages = listOf(
        R.drawable.veges,
        R.drawable.fruits,
        R.drawable.grains
    )

    val adTexts = listOf(
        "Get fresh vegetables now\n Order Now!!!",
        "High-quality and Farm Fresh fruits available at your fingertips",
        "Order your grains today and enjoy with your family!!"
    )

    var currentImageIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(currentImageIndex) {
        delay(3000L)
        currentImageIndex = (currentImageIndex + 1) % cropImages.size
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(id = cropImages[currentImageIndex]),
            contentDescription = "Crop Ad",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
                .padding(8.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                text = adTexts[currentImageIndex],
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
fun CategoryList(filteredCategories: List<String>, navController: NavController) {
    val categoriesWithImages = listOf(
        "Vegetables" to R.drawable.vegetables,
        "Fruits" to R.drawable.fruit,
        "Cereals & Grains" to R.drawable.corn,
        "Poultry Products" to R.drawable.egs,
        "Spices" to R.drawable.spices,
        "Dairy Products" to R.drawable.milk,
        "Farm Inputs" to R.drawable.milk
    )

    val categoriesToDisplay = if (filteredCategories.isEmpty()) {
        categoriesWithImages
    } else {
        categoriesWithImages.filter { it.first in filteredCategories }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        categoriesToDisplay.forEach { (category, imageResId) ->
            CategoryItem(category = category, imageResId = imageResId) {
                navController.navigate("category_detail/$category")
            }
        }
    }
}

@Composable
fun CategoryItem(category: String, imageResId: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.LightGray)
            .clickable { onClick() }
            .shadow(0.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = category,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f),
            )


            Image(
                painter = painterResource(id = imageResId),
                contentDescription = category,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(80.dp)
                    .weight(1f),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MarketplaceScreenPreview() {
    Surface {
        val navController = rememberNavController()
        MarketplaceScreen(navController = navController)
    }
}
