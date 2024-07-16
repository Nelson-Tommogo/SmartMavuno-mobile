package com.example.smartmavuno.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartmavuno.model.CalendarDataSource
import com.example.smartmavuno.model.CalendarUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class CalendarViewModel : ViewModel() {
    private val dataSource by lazy { CalendarDataSource() }

    val _uiState = MutableStateFlow(CalendarUiState.Init)
    val uiState: StateFlow<CalendarUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            updateCalendarState(_uiState.value.yearMonth)
        }
    }

    private suspend fun updateCalendarState(yearMonth: YearMonth) {
        _uiState.update { currentState ->
            currentState.copy(
                dates = dataSource.getDates(yearMonth),
                events = getEventsForMonth(yearMonth)
            )
        }
    }

    private fun getEventsForMonth(yearMonth: YearMonth): List<com.example.smartmavuno.viewmodel.Event> {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
        return listOf(
            Event(LocalDate.parse("2024-07-18T10:00", formatter), "Meeting with Suppliers"),
            Event(LocalDate.parse("2024-07-20T15:00", formatter), "Product Demo"),
            Event(LocalDate.parse("2024-07-22T09:30", formatter), "Strategy Review")
        )
    }

    fun toNextMonth(nextMonth: YearMonth) {
        viewModelScope.launch {
            updateCalendarState(nextMonth)
        }
    }

    fun toPreviousMonth(prevMonth: YearMonth) {
        viewModelScope.launch {
            updateCalendarState(prevMonth)
        }
    }
}
