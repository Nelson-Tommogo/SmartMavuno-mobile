import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartmavuno.R
import java.time.LocalDate
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
    selectedDate: LocalDate,
    events: List<Event>,
    onDateSelected: (LocalDate) -> Unit,
    onEventClicked: (Event) -> Unit
) {
    val currentMonth = YearMonth.from(selectedDate)
    val daysInMonth = currentMonth.lengthOfMonth()
    val firstDay = currentMonth.atDay(1).dayOfWeek.value % 7 // Start day of the week
    val weekFields = WeekFields.of(Locale.getDefault())

    // Generate days of the week labels
    val daysOfWeek = (1..7).map { currentMonth.atDay(it).dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()) }

    val calendarDays = mutableListOf<CalendarDay>()

    for (dayOfMonth in 1..daysInMonth) {
        val date = currentMonth.atDay(dayOfMonth)
        val eventsForDay = events.filter { it.date == date }
        calendarDays.add(CalendarDay(date = date, events = eventsForDay))
    }

    Column(modifier = Modifier.fillMaxSize()) {
        CalendarHeader(currentMonth = currentMonth)
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            item {
                DayOfWeekRow(daysOfWeek = daysOfWeek)
            }
            items(calendarDays.chunked(7)) { week ->
                WeekRow(week = week, selectedDate = selectedDate, onDateSelected = onDateSelected, onEventClicked = onEventClicked)
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
            .height(64.dp)
            .background(if (isSelected) Color.LightGray else Color.White)
            .clickable(onClick = onDateSelected)
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = calendarDay.date.dayOfMonth.toString(),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            calendarDay.events.forEach { event ->
//                CalendarEventItem(
//                    event = event,
//                    onClick = { onEventClicked(event) }
//                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarHeader(currentMonth: YearMonth) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { /* Handle previous month */ }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_add_circle_outline_24),
                contentDescription = "Previous Month"
            )
        }
        Text(
            text = currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault()),
            style = MaterialTheme.typography.bodyMedium
        )
        IconButton(
            onClick = { /* Handle next month */ }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_add_circle_outline_24),
                contentDescription = "Next Month"
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun CalendarViewPreview() {
    val events = listOf(
        Event("Meeting", LocalDate.now()),
        Event("Presentation", LocalDate.now().plusDays(1)),
        Event("Birthday Party", LocalDate.now().plusDays(2))
    )
    CalendarView(
        selectedDate = LocalDate.now(),
        events = events,
        onDateSelected = {},
        onEventClicked = {}
    )
}
