package com.x.trainmodx.domain.model

data class TrainingDay(
    val id: Long = 0,
    val weekId: Long,
    val dayOfWeek: DayOfWeek,
    val label: String = ""
)

enum class DayOfWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}