//package com.example.smartmavuno.viewmodel
//
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//@Composable
//fun CartScreen(
//    viewModel: MarketplaceViewModel = viewModel()
//) {
//    val cartItems by viewModel.cart.collectAsState()
//
//    Column {
//        Text(
//            text = "Cart",
//            style = MaterialTheme.typography.bodyMedium,
//            modifier = Modifier.padding(16.dp)
//        )
//        LazyColumn(
//            modifier = Modifier.fillMaxSize(),
//            contentPadding = PaddingValues(16.dp)
//        ) {
//            items(cartItems.size) { index ->
//                CartItem(service = cartItems[index])
//                Spacer(modifier = Modifier.height(8.dp))
//            }
//        }
//    }
//}
//
//@Composable
//fun CartItem(service: Service) {
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//    ) {
//        Row(modifier = Modifier.padding(16.dp)) {
//            Image(
//                painter = painterResource(id = service.iconRes),
//                contentDescription = service.name,
//                modifier = Modifier.size(64.dp)
//            )
//            Spacer(modifier = Modifier.width(16.dp))
//            Column {
//                Text(text = service.name, style = MaterialTheme.typography.bodyMedium)
//                Text(text = service.price, style = MaterialTheme.typography.bodyMedium)
//                Text(text = "Rating: ${service.rating}", style = MaterialTheme.typography.bodyMedium)
//            }
//        }
//    }
//}
