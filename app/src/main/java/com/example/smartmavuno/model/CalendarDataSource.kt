package com.example.smartmavuno.model


import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.YearMonth
import java.time.DayOfWeek
import java.time.LocalTime

class CalendarDataSource {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getDates(yearMonth: YearMonth): List<CalendarUiState.Date> {
        return yearMonth.getDayOfMonthStartingFromMonday()
            .map { date ->
                CalendarUiState.Date(
                    dayOfMonth = if (date.monthValue == yearMonth.monthValue) {
                        "${date.dayOfMonth}"
                    } else {
                        "" // Fill with an empty string for days outside the current month
                    },
                    isSelected = date.isEqual(LocalDate.now()) && date.monthValue == yearMonth.monthValue
                )
            }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun YearMonth.getDayOfMonthStartingFromMonday(): List<LocalDate> {
    val firstDayOfMonth = LocalDate.of(year, month, 1)
    val firstMondayOfMonth = firstDayOfMonth.with(DayOfWeek.MONDAY)
    val firstDayOfNextMonth = firstDayOfMonth.plusMonths(1)

    return generateSequence(firstMondayOfMonth) { it.plusDays(1) }
        .takeWhile { it.isBefore(firstDayOfNextMonth) }
        .toList()
}

data class Event(
    val name: String,
    val date: LocalDate,
    val time: LocalTime,
    val isRepeating: Boolean
)



