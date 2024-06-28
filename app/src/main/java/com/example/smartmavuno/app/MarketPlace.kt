package com.example.smartmavuno.app

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.smartmavuno.R  // Replace with your project's resource import

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val rating: Float,
    val imageUrl: String
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MarketplaceScreen(products: NavHostController) {

}

@Composable
fun ProductItem(product: Product) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(240.dp), // Adjust height as needed
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = R.drawable.marketplace), // Replace with actual image
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp), // Adjust height as needed
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.name, fontSize = 18.sp, color = Color.Black)
            Text(text = "$${product.price}", fontSize = 16.sp, color = Color.Gray)
            Row(verticalAlignment = Alignment.CenterVertically) {
                repeat(product.rating.toInt()) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = Color.Yellow,
                        modifier = Modifier.size(18.dp)
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "${product.rating}", fontSize = 14.sp, color = Color.Gray)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /* Handle add to cart action */ },
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Add to Cart",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Add to Cart", color = Color.White)
            }
        }
    }
}

@Preview
@Composable
fun MarketplaceScreenPreview() {
    val products = remember {
        mutableStateListOf(
            Product(1, "com.example.smartmavuno.app.Product 1", 10.0, 4.5f, ""),
            Product(2, "com.example.smartmavuno.app.Product 2", 20.0, 4.0f, ""),
            Product(3, "com.example.smartmavuno.app.Product 3", 30.0, 3.5f, ""),
            Product(4, "com.example.smartmavuno.app.Product 4", 15.0, 4.2f, ""),
            Product(5, "com.example.smartmavuno.app.Product 5", 25.0, 3.8f, "")
            // Add more products as needed
        )
    }
}
