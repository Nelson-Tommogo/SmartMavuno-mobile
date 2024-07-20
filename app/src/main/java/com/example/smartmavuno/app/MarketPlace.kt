package com.example.smartmavuno.app

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartmavuno.R
import com.example.smartmavuno.viewmodel.MarketplaceViewModel
import com.example.smartmavuno.viewmodel.Service
import com.example.smartmavuno.viewmodel.SortBy
import com.example.smartmavuno.ui.theme.green1
import com.example.smartmavuno.ui.theme.green3
import com.example.smartmavuno.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketplaceScreen(
    navController: NavController,
    viewModel: MarketplaceViewModel = viewModel()
) {
    val grey = colorResource(id = R.color.grey)
    val green1 = colorResource(id = R.color.green1)
    val green2 = colorResource(id = R.color.green2)
    val green3 = colorResource(id = R.color.green3)
    var searchQuery by remember { mutableStateOf("") }
    var showFilterMenu by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
            .padding(16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "Back Icon",
                    tint = green1,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "MarketPlace",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.baseline_shopping_cart_24),
                contentDescription = "Cart Icon",
                tint = green1,
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        // Handle cart click here
                        navController.navigate("cart_screen") // Example navigation
                    }
            )
        }

        // Search Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(green1)
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(green3, shape = RoundedCornerShape(10))
                        .padding(6.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_search_24),
                        contentDescription = "Search Icon",
                        tint = green1,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = searchQuery,
                    onValueChange = { newValue ->
                        searchQuery = newValue
                        viewModel.filterByName(newValue)
                    },
                    placeholder = { Text("Search Service", color = Color.Black) },
                    modifier = Modifier
                        .weight(1f)
                        .background(green1)
                        .padding(horizontal = 8.dp),
                    textStyle = TextStyle(color = Color.Black),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black,
                    )
                )
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(4.dp))
                        .clickable { showFilterMenu = !showFilterMenu }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_sort_24),
                        contentDescription = "Filter Icon",
                        tint = green1,
                        modifier = Modifier.size(24.dp)
                    )
                    DropdownMenu(
                        expanded = showFilterMenu,
                        onDismissRequest = { showFilterMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Sort by Ratings") },
                            onClick = {
                                viewModel.updateSortBy(SortBy.Ratings)
                                showFilterMenu = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Sort by Price") },
                            onClick = {
                                viewModel.updateSortBy(SortBy.Price)
                                showFilterMenu = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Sort by Frequently Ordered") },
                            onClick = {
                                viewModel.updateSortBy(SortBy.FrequentlyOrdered)
                                showFilterMenu = false
                            }
                        )
                    }
                }
            }
        }

        // Display Products or "Product not available" message
        if (viewModel.filteredResultsAvailable) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(grey)
            ) {
                items(viewModel.services.size) { index ->
                    ServiceBox(
                        service = viewModel.services[index],
                        onClick = {
                            viewModel.addToCart(viewModel.services[index])
                        }
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Product not available",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun ServiceBox(service: Service, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .height(150.dp)
            .width(200.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(green3)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = service.iconRes),
                contentDescription = service.name,
                modifier = Modifier.size(50.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = service.name,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            if (service.originalPrice.isNotEmpty() && service.originalPrice != service.price) {
                Text(
                    text = "Ksh. ${service.originalPrice}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        textDecoration = TextDecoration.LineThrough,
                        color = Color.Gray
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(2.dp))
            }
            Box(
                modifier = Modifier
                    .background(green1, shape = CircleShape)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Ksh. ${service.price}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White
                    ),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.baseline_shopping_cart_24),
                contentDescription = "Add to Cart Icon",
                tint = green1,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = onClick)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MarketPlacePreview() {
    MarketplaceScreen(navController = rememberNavController())
}
