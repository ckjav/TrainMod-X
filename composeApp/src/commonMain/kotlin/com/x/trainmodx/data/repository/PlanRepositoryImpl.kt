package com.x.trainmodx.data.repository

import com.x.trainmodx.db.TrainModX
import com.x.trainmodx.domain.model.Plan
import com.x.trainmodx.domain.repository.PlanRepository

class PlanRepositoryImpl(private val db: TrainModX) : PlanRepository {

    override suspend fun createPlan(plan: Plan): Long {
        db.planQueries.insert(
            name = plan.name,
            startDate = plan.startDate,
            totalWeeks = plan.totalWeeks.toLong(),
            isActive = if (plan.isActive) 1L else 0L
        )
        return db.planQueries.getAll().executeAsList().last().id
    }

    override suspend fun getActivePlan(): Plan? {
        return db.planQueries.getActive().executeAsOneOrNull()?.toDomain()
    }

    override suspend fun getAllPlans(): List<Plan> {
        return db.planQueries.getAll().executeAsList().map { it.toDomain() }
    }

    override suspend fun updatePlan(plan: Plan) {
        db.planQueries.updatePlan(
            name = plan.name,
            startDate = plan.startDate,
            totalWeeks = plan.totalWeeks.toLong(),
            isActive = if (plan.isActive) 1L else 0L,
            id = plan.id
        )
    }

    override suspend fun deletePlan(id: Long) {
        db.planQueries.deleteById(id)
    }

    override suspend fun setActivePlan(id: Long) {
        db.planQueries.deactivateAll()
        db.planQueries.setActive(id)
    }

    private fun com.x.trainmodx.db.Plan.toDomain() = Plan(
        id = id,
        name = name,
        startDate = startDate,
        totalWeeks = totalWeeks.toInt(),
        isActive = isActive == 1L
    )
}