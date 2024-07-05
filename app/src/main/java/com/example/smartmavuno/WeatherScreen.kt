import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class WeatherResponse(
    val main: WeatherMain,
    val weather: List<WeatherDescription>,
    val name: String
)

data class WeatherMain(
    val temp: Double,
    val feelsLike: Double
)

data class WeatherDescription(
    val main: String,
    val description: String
)

interface WeatherService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") location: String,
        @Query("appid") apiKey: String
    ): WeatherResponse
}

class WeatherViewModel : ViewModel() {
    private val service = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherService::class.java)

    private val apiKey = "4f0f69ddd613951d8005afa5d12fdb97"

    private val _weather = mutableStateOf<WeatherResponse?>(null)
    val weather: State<WeatherResponse?> = _weather

    fun getWeather(location: String) {
        viewModelScope.launch {
            try {
                val response = service.getWeather(location, apiKey)
                _weather.value = response
            } catch (e: Exception) {
                // Handle error
                Log.e("WeatherViewModel", "Error fetching weather", e)
            }
        }
    }
}

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = viewModel()) {
    var location by remember { mutableStateOf("Nairobi") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        TextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Enter location") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { viewModel.getWeather(location) },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Get Weather")
        }

        val weather by viewModel.weather.collectAsState()
        weather?.let {
            Text(
                text = "Weather in ${it.name}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = "Temperature: ${it.main.temp} °C",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Feels like: ${it.main.feelsLike} °C",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Description: ${it.weather.firstOrNull()?.description}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
fun PreviewWeatherScreen() {
    WeatherScreen()
}
