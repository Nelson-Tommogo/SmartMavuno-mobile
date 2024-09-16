package com.example.smartmavuno.viewmodel

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

    private var _selectedCategory: String by mutableStateOf("All")
    val selectedCategory: String
        get() = _selectedCategory

    private var _services: List<Service> by mutableStateOf(getDummyServices())
    val services: List<Service>
        get() = _services

    private var _filteredResultsAvailable by mutableStateOf(true)
    val filteredResultsAvailable: Boolean
        get() = _filteredResultsAvailable

    init {
        updateServices()
    }

//    fun updateSortBy(newSortBy: String) {
//        _sortBy = newSortBy
//        updateServices()
//    }

    fun updateCategory(newCategory: String) {
        _selectedCategory = newCategory
        updateServices()
    }

    fun addToCart(service: Service) {
        cart.add(service)
    }

    fun filterByName(name: String) {
        _services = getDummyServices().filter {
            it.name.contains(name, ignoreCase = true) &&
                    (it.category == _selectedCategory || _selectedCategory == "All")
        }
        _filteredResultsAvailable = _services.isNotEmpty()
    }

    private fun updateServices() {
        _services = getDummyServices().filter {
            it.category == _selectedCategory || _selectedCategory == "All"
        }.sortedBy {
            when (_sortBy) {
                SortBy.Ratings -> -it.rating
                SortBy.Price -> it.price.removePrefix("KES ").toFloat()
                SortBy.FrequentlyOrdered -> 0f // Assuming no sorting needed here
            }
        }
    }

    private fun getDummyServices(): List<Service> {
        return listOf(
            Service("Waru", R.drawable.waru, "KES 100", 4.5f, "Vegetables"),
            Service("Potatoes", R.drawable.potatoes, "KES 200", 4.0f, "Vegetables"),
            Service("Cabbage", R.drawable.potatoes, "KES 150", 3.5f, "Vegetables"),
            Service("Carrots", R.drawable.potatoes, "KES 120", 4.2f, "Vegetables"),
            Service("Cassava", R.drawable.potatoes, "KES 180", 4.8f, "Vegetables"),
            Service("Maize", R.drawable.maize, "KES 250", 4.1f, "Vegetables"),
            Service("Melon", R.drawable.melon, "KES 300", 3.9f, "Fruits"),
            Service("Onions", R.drawable.onions, "KES 80", 4.3f, "Vegetables"),
            Service("Guavas", R.drawable.guava, "KES 100", 4.5f, "Fruits"),
            Service("Tomatoes", R.drawable.tomatoes, "KES 200", 4.0f, "Vegetables"),
            Service("Beans", R.drawable.potatoes, "KES 180", 4.2f, "Vegetables"),
            Service("Lettuce", R.drawable.lettuce, "KES 150", 4.0f, "Vegetables"),
            Service("Peas", R.drawable.peas, "KES 160", 4.3f, "Vegetables"),
            Service("Spinach", R.drawable.spinach, "KES 120", 4.1f, "Vegetables")
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
    val rating: Float,
    val category: String,
    val originalPrice: String = "",
)
