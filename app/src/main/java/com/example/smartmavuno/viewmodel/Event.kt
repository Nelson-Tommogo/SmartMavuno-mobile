package com.example.smartmavuno.viewmodel


import java.time.LocalDateTime

data class Event(
    val dateTime: LocalDateTime,
    val name: String,
    val repeat: Boolean
)
