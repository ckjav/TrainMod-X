package com.x.trainmodx.data.repository

import com.x.trainmodx.db.TrainModX
import com.x.trainmodx.domain.model.DayOfWeek
import com.x.trainmodx.domain.model.TrainingDay
import com.x.trainmodx.domain.repository.TrainingDayRepository

class TrainingDayRepositoryImpl(private val db: TrainModX) : TrainingDayRepository {

    override suspend fun createTrainingDay(day: TrainingDay): Long {
        db.trainingDayQueries.insert(
            weekId = day.weekId,
            dayOfWeek = day.dayOfWeek.name,
            label = day.label
        )
        return db.trainingDayQueries.getByWeek(day.weekId).executeAsList().last().id
    }

    override suspend fun getDaysByWeek(weekId: Long): List<TrainingDay> {
        return db.trainingDayQueries.getByWeek(weekId).executeAsList().map { it.toDomain() }
    }

    override suspend fun updateTrainingDay(day: TrainingDay) {
        db.trainingDayQueries.update(
            dayOfWeek = day.dayOfWeek.name,
            label = day.label,
            id = day.id
        )
    }

    override suspend fun deleteTrainingDay(id: Long) {
        db.trainingDayQueries.deleteById(id)
    }

    override suspend fun copyTrainingDay(
        sourceDayId: Long,
        targetWeekId: Long,
        targetDayOfWeek: DayOfWeek
    ): Long {
        val source = db.trainingDayQueries.getById(sourceDayId).executeAsOneOrNull() ?: return -1L
        db.trainingDayQueries.insert(
            weekId = targetWeekId,
            dayOfWeek = targetDayOfWeek.name,
            label = source.label
        )
        return db.trainingDayQueries.getByWeek(targetWeekId).executeAsList().last().id
    }

    private fun com.x.trainmodx.db.TrainingDay.toDomain() = TrainingDay(
        id = id,
        weekId = weekId,
        dayOfWeek = DayOfWeek.valueOf(dayOfWeek),
        label = label
    )
}