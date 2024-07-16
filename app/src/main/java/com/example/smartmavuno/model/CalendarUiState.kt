package com.example.smartmavuno.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.YearMonth

data class CalendarUiState(
    val yearMonth: YearMonth,
    val dates: List<Date>,
    val events: List<com.example.smartmavuno.viewmodel.Event> = emptyList() // Include events in the state
) {
    data class Date(
        val dayOfMonth: String,
        val isSelected: Boolean
    )

    data class Event(
        val dateTime: String,
        val name: String
    )

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        val Init = CalendarUiState(
            yearMonth = YearMonth.now(),
            dates = emptyList(),
            events = emptyList()
        )
    }
}
