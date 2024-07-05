package com.example.smartmavuno.app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartmavuno.R
import com.example.smartmavuno.ui.theme.green1
import com.example.smartmavuno.ui.theme.green3
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth
import java.time.format.TextStyle
import java.time.temporal.WeekFields
import java.util.*

// Data classes for events and calendar day
data class Event(
    val title: String,
    val date: LocalDate
)

data class CalendarDay(
    val date: LocalDate,
    val events: List<Event> = emptyList()
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarView(
    navController: NavController,
    selectedDate: LocalDate,
    events: List<Event>,
    onDateSelected: (LocalDate) -> Unit,
    onEventClicked: (Event) -> Unit,
    onAddEventClicked: () -> Unit
) {
    var currentMonth by remember { mutableStateOf(YearMonth.from(selectedDate)) }
    val daysInMonth = currentMonth.lengthOfMonth()
    val firstDay = currentMonth.atDay(1).dayOfWeek.value % 7 // Start day of the week
    val weekFields = WeekFields.of(Locale.getDefault())

    // Generate days of the week labels
    val daysOfWeek = (1..7).map { currentMonth.atDay(it).dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()) }

    val calendarDays = remember(currentMonth) {
        (1..daysInMonth).map { dayOfMonth ->
            val date = currentMonth.atDay(dayOfMonth)
            val eventsForDay = events.filter { it.date == date }
            CalendarDay(date = date, events = eventsForDay)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "SmartMavuno Farmer Calendar",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(green3)
                .padding(vertical = 8.dp)
        )

        CalendarHeader(
            currentMonth = currentMonth,
            onAddEventClicked = onAddEventClicked,
            onPreviousMonth = {
                currentMonth = currentMonth.minusMonths(1)
            },
            onNextMonth = {
                currentMonth = currentMonth.plusMonths(1)
            }
        )
        LazyColumn(
            modifier = Modifier.weight(1f)
                .background(green3)
        ) {
            item {
                DayOfWeekRow(daysOfWeek = daysOfWeek)
            }
            items(calendarDays.chunked(7)) { week ->
                WeekRow(week = week, selectedDate = selectedDate, onDateSelected = onDateSelected, onEventClicked = onEventClicked)
            }
            item {
                Spacer(modifier = Modifier.height(48.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom
                ) {
                    IconButton(
                        onClick = onAddEventClicked
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clickable(onClick = onAddEventClicked)
                                .background(green1, CircleShape)
                                .padding(8.dp)
                                .align(Alignment.CenterVertically)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_add_circle_outline_24),
                                contentDescription = "Add Event",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DayOfWeekRow(daysOfWeek: List<String>) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        daysOfWeek.forEach { dayOfWeek ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .background(Color.Gray)
                    .padding(4.dp)
            ) {
                Text(
                    text = dayOfWeek,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeekRow(
    week: List<CalendarDay>,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    onEventClicked: (Event) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        week.forEach { calendarDay ->
            CalendarDayItem(
                calendarDay = calendarDay,
                isSelected = calendarDay.date == selectedDate,
                onDateSelected = { onDateSelected(calendarDay.date) },
                onEventClicked = onEventClicked
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarDayItem(
    calendarDay: CalendarDay,
    isSelected: Boolean,
    onDateSelected: () -> Unit,
    onEventClicked: (Event) -> Unit
) {
    Box(
        modifier = Modifier
            .height(72.dp)
            .background(green3)
            .clickable(onClick = onDateSelected)
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
                .background(green3),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = calendarDay.date.dayOfMonth.toString(),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            calendarDay.events.forEach { event ->
                CalendarEventItem(
                    event = event,
                    onClick = { onEventClicked(event) }
                )
            }
        }
    }
}

@Composable
fun CalendarEventItem(event: Event, onClick: () -> Unit) {
    Text(
        text = event.title,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(vertical = 2.dp)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarHeader(
    currentMonth: YearMonth,
    onAddEventClicked: () -> Unit,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(green1)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onPreviousMonth
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_chevron_left_24),
                contentDescription = "Previous Month",
                tint = Color.White
            )
        }
        Text(
            text = currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault()),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = currentMonth.year.toString(),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        IconButton(
            onClick = onNextMonth
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_chevron_right_24),
                contentDescription = "Next Month",
                tint = Color.White
            )
        }
    }
}

@Preview
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarViewPreview() {
    val events = listOf(
        Event("Meeting", LocalDate.now()),
        Event("Presentation", LocalDate.now().plusDays(1)),
        Event("Birthday Party", LocalDate.now().plusDays(2))
    )
    CalendarView(
        navController = rememberNavController(),
        selectedDate = LocalDate.now(),
        events = events,
        onDateSelected = {},
        onEventClicked = {},
        onAddEventClicked = {}
    )
}
