package com.example.smartmavuno.app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartmavuno.R
import com.example.smartmavuno.model.CalendarUiState
import com.example.smartmavuno.ui.theme.black
import com.example.smartmavuno.ui.theme.green3
import com.example.smartmavuno.util.DateUtils
import com.example.smartmavuno.viewmodel.CalendarViewModel
import com.example.smartmavuno.viewmodel.Event
import java.time.DayOfWeek
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen(
    navController: NavController,
    viewModel: CalendarViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Header(
            yearMonth = uiState.yearMonth,
            onPreviousMonthButtonClicked = { viewModel.toPreviousMonth(it) },
            onNextMonthButtonClicked = { viewModel.toNextMonth(it) }
        )

        WeekdayLabels()

        Content(
            dates = uiState.dates,
            onDateClickListener = { date ->
                // Handle date click
            }
        )

        FloatingActionButton(
            onClick = { /* Add event */ },
            modifier = Modifier
                .align(Alignment.End)
                .padding(vertical = 16.dp)
                .background(color = green3, shape = CircleShape),
            contentColor = black
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Event")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display upcoming events
        UpcomingEvents(events = uiState.events)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Header(
    yearMonth: YearMonth,
    onPreviousMonthButtonClicked: (YearMonth) -> Unit,
    onNextMonthButtonClicked: (YearMonth) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .background(color = Color(0xFFB8CAB3), shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SmartMavunars Calendar \uD83D\uDCC5",
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.manrope_variablefont_wght)),
                    color = Color.Black
                ),
                modifier = Modifier.padding(top = 8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onPreviousMonthButtonClicked(yearMonth.minusMonths(1)) }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Previous Month")
                }
                Text(
                    text = "${yearMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${yearMonth.year}",
                    style = androidx.compose.ui.text.TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
                IconButton(onClick = { onNextMonthButtonClicked(yearMonth.plusMonths(1)) }) {
                    Icon(Icons.Default.ArrowForward, contentDescription = "Next Month")
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeekdayLabels() {
    Row(modifier = Modifier.fillMaxWidth()) {
        daysOfWeek.forEach { day ->
            Text(
                text = day,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun Content(
    dates: List<CalendarUiState.Date>,
    onDateClickListener: (CalendarUiState.Date) -> Unit
) {
    Column {
        var index = 0
        repeat(6) {
            if (index >= dates.size) return@repeat
            Row {
                repeat(7) {
                    val item = if (index < dates.size) dates[index] else CalendarUiState.Date("", false)
                    ContentItem(
                        date = item,
                        onClickListener = onDateClickListener,
                        modifier = Modifier.weight(1f)
                    )
                    index++
                }
            }
        }
    }
}

@Composable
fun ContentItem(
    date: CalendarUiState.Date,
    onClickListener: (CalendarUiState.Date) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = if (date.isSelected) {
                    MaterialTheme.colorScheme.secondaryContainer
                } else {
                    Color.Transparent
                },
                shape = RoundedCornerShape(8.dp) // Adjust the radius as needed
            )
            .clickable { onClickListener(date) }
            .padding(8.dp)
    ) {
        Text(
            text = date.dayOfMonth,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UpcomingEvents(events: List<Event>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color(0xFFB8CAB3), shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Upcoming Events",
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.manrope_variablefont_wght)),
                    color = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                textAlign = TextAlign.Center
            )

            events.forEach { event ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = DateUtils.formatDate(event.dateTime),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = event.name,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

val daysOfWeek: Array<String>
    @RequiresApi(Build.VERSION_CODES.O)
    get() {
        val daysOfWeek = Array(7) { "" }
        for (dayOfWeek in DayOfWeek.values()) {
            val localizedDayName = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
            daysOfWeek[dayOfWeek.value - 1] = localizedDayName
        }
        return daysOfWeek
    }

@Composable
fun previewViewModel(): CalendarViewModel {
    val viewModelStoreOwner = LocalViewModelStoreOwner.current
    val factory = object : ViewModelProvider.Factory {
        @RequiresApi(Build.VERSION_CODES.O)
        @Suppress("UNCHECKED_CAST")
        override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
            return CalendarViewModel().apply {
                _uiState.value = CalendarUiState(
                    yearMonth = YearMonth.now(),
                    dates = (1..30).map { day ->
                        CalendarUiState.Date(
                            dayOfMonth = day.toString(),
                            isSelected = false
                        )
                    }
                )
            } as T
        }
    }
    return viewModel(factory = factory, viewModelStoreOwner = viewModelStoreOwner!!)
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    val navController = rememberNavController()
    val viewModel: CalendarViewModel = previewViewModel()

    // Wrap the CalendarScreen with NavHost
    NavHost(navController = navController, startDestination = "calendar_screen") {
        composable("calendar_screen") {
            CalendarScreen(navController = navController, viewModel = viewModel)
        }
        // Define other destinations as needed
    }
}

