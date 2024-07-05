package com.example.smartmavuno.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.smartmavuno.R

class MarketplaceViewModel : ViewModel() {
    private val cart = mutableStateListOf<Service>()

    private var _sortBy: SortBy by mutableStateOf(SortBy.Ratings)
    val sortBy: SortBy
        get() = _sortBy

    private var _services: List<Service> by mutableStateOf(getDummyServices())
    val services: List<Service>
        get() = _services

    private var _filteredResultsAvailable by mutableStateOf(true)
    val filteredResultsAvailable: Boolean
        get() = _filteredResultsAvailable

    init {
        updateServices()
    }

    fun updateSortBy(newSortBy: SortBy) {
        _sortBy = newSortBy
        updateServices()
    }

    fun addToCart(service: Service) {
        cart.add(service)
    }

    fun filterByName(name: String) {
        _services = getDummyServices().filter {
            it.name.contains(name, ignoreCase = true)
        }
        _filteredResultsAvailable = _services.isNotEmpty()
    }

    private fun updateServices() {
        _services = when (_sortBy) {
            SortBy.Ratings -> getDummyServices().sortedByDescending { it.rating }
            SortBy.Price -> getDummyServices().sortedBy { it.price.removePrefix("KES ").toFloat() }
            SortBy.FrequentlyOrdered -> getDummyServices()
        }
    }

    private fun getDummyServices(): List<Service> {
        return listOf(
            Service("Waru", R.drawable.waru, "KES 100", 4.5f),
            Service("Potatoes", R.drawable.potatoes, "KES 200", 4.0f),
            Service("Cabbage", R.drawable.cabbage, "KES 150", 3.5f),
            Service("Carrots", R.drawable.carrots, "KES 120", 4.2f),
            Service("Cassava", R.drawable.cassava, "KES 180", 4.8f),
            Service("Maize", R.drawable.maize, "KES 250", 4.1f),
            Service("Melon", R.drawable.melon, "KES 300", 3.9f),
            Service("Onions", R.drawable.onions, "KES 80", 4.3f),
            Service("Guavas", R.drawable.guava, "KES 100", 4.5f),
            Service("Tomatoes", R.drawable.tomatoes, "KES 200", 4.0f),
            Service("Beans", R.drawable.beans, "KES 180", 4.2f),
            Service("Lettuce", R.drawable.lettuce, "KES 150", 4.0f),
            Service("Peas", R.drawable.peas, "KES 160", 4.3f),
            Service("Spinach", R.drawable.spinach, "KES 120", 4.1f)

        )
    }
}

enum class SortBy {
    Ratings, Price, FrequentlyOrdered
}

data class Service(
    val name: String,
    val iconRes: Int,
    val price: String,
    val rating: Float
)
