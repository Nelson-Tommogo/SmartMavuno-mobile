package com.example.smartmavuno.viewmodel


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ServiceCard(
    service: Service,
    onAddToCart: (Service) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onAddToCart(service) }
            .padding(8.dp),
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = service.iconRes),
                contentDescription = service.name,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = service.name, style = MaterialTheme.typography.bodyMedium)
                Text(text = service.price, style = MaterialTheme.typography.bodyMedium)
                Text(text = "Rating: ${service.rating}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
