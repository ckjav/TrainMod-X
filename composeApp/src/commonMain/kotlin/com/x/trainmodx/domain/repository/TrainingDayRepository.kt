package com.x.trainmodx.domain.repository

import com.x.trainmodx.domain.model.DayOfWeek
import com.x.trainmodx.domain.model.TrainingDay

interface TrainingDayRepository {
    suspend fun createTrainingDay(day: TrainingDay): Long
    suspend fun getDaysByWeek(weekId: Long): List<TrainingDay>
    suspend fun updateTrainingDay(day: TrainingDay)
    suspend fun deleteTrainingDay(id: Long)
    suspend fun copyTrainingDay(sourceDayId: Long, targetWeekId: Long, targetDayOfWeek: DayOfWeek): Long
}