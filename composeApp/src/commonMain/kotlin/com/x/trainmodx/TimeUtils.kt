package com.x.trainmodx

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun currentTimeMillis(): Long = Clock.System.now().toEpochMilliseconds()

fun currentDayOfWeek(): com.x.trainmodx.domain.model.DayOfWeek {
    val now = Clock.System.now()
    val localDate = now.toLocalDateTime(TimeZone.currentSystemDefault())
    return when (localDate.dayOfWeek) {
        kotlinx.datetime.DayOfWeek.MONDAY -> com.x.trainmodx.domain.model.DayOfWeek.MONDAY
        kotlinx.datetime.DayOfWeek.TUESDAY -> com.x.trainmodx.domain.model.DayOfWeek.TUESDAY
        kotlinx.datetime.DayOfWeek.WEDNESDAY -> com.x.trainmodx.domain.model.DayOfWeek.WEDNESDAY
        kotlinx.datetime.DayOfWeek.THURSDAY -> com.x.trainmodx.domain.model.DayOfWeek.THURSDAY
        kotlinx.datetime.DayOfWeek.FRIDAY -> com.x.trainmodx.domain.model.DayOfWeek.FRIDAY
        kotlinx.datetime.DayOfWeek.SATURDAY -> com.x.trainmodx.domain.model.DayOfWeek.SATURDAY
        else -> com.x.trainmodx.domain.model.DayOfWeek.MONDAY
    }
}