// TopBar.kt
package com.example.smartmavuno.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartmavuno.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    cartItemCount: Int = 0,
    onMenuClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onCartClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = "SmartMavuno",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color.Black,
                    fontSize = 20.sp
                ),
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    painter = painterResource(id = R.drawable.logo_no_background),
                    contentDescription = "logo",
                    tint = Color.Unspecified
                )
            }
        },
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    painter = painterResource(id = R.drawable.menu),
                    contentDescription = "menu",
                    tint = Color.Unspecified
                )
            }
            Box {
                IconButton(onClick = onCartClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.cart),
                        contentDescription = "cart",
                        tint = Color.Unspecified
                    )
                }
                if (cartItemCount > 0) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(Color.Red, shape = CircleShape)
                            .align(Alignment.TopEnd)
                    ) {
                        Text(
                            text = "$cartItemCount",
                            color = Color.White,
                            fontSize = 10.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.primary)
    )
}

@Preview
@Composable
fun TopBarPreview() {
    TopBar(cartItemCount = 1)
}
