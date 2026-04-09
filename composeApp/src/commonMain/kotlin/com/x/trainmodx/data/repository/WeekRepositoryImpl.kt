package com.x.trainmodx.data.repository

import com.x.trainmodx.db.TrainModX
import com.x.trainmodx.domain.model.Week
import com.x.trainmodx.domain.repository.WeekRepository

class WeekRepositoryImpl(private val db: TrainModX) : WeekRepository {

    override suspend fun createWeek(week: Week): Long {
        db.weekQueries.insert(
            planId = week.planId,
            number = week.number.toLong(),
            isDeload = if (week.isDeload) 1L else 0L
        )
        return db.weekQueries.getByPlan(week.planId).executeAsList().last().id
    }

    override suspend fun getWeeksByPlan(planId: Long): List<Week> {
        return db.weekQueries.getByPlan(planId).executeAsList().map { it.toDomain() }
    }

    override suspend fun updateWeek(week: Week) {
        db.weekQueries.update(
            isDeload = if (week.isDeload) 1L else 0L,
            id = week.id
        )
    }

    override suspend fun deleteWeek(id: Long) {
        db.weekQueries.deleteById(id)
    }

    private fun com.x.trainmodx.db.Week.toDomain() = Week(
        id = id,
        planId = planId,
        number = number.toInt(),
        isDeload = isDeload == 1L
    )
}