package com.x.trainmodx.domain.usecase.plan

import com.x.trainmodx.domain.model.Plan
import com.x.trainmodx.domain.model.Week
import com.x.trainmodx.domain.repository.PlanRepository
import com.x.trainmodx.domain.repository.WeekRepository

class CreatePlanUseCase(
    private val planRepository: PlanRepository,
    private val weekRepository: WeekRepository
) {
    suspend operator fun invoke(name: String, totalWeeks: Int, startDate: Long): Long {
        val planId = planRepository.createPlan(
            Plan(
                name = name,
                startDate = startDate,
                totalWeeks = totalWeeks,
                isActive = true
            )
        )
        repeat(totalWeeks) { index ->
            weekRepository.createWeek(
                Week(
                    planId = planId,
                    number = index + 1,
                    isDeload = index == totalWeeks - 1
                )
            )
        }
        return planId
    }
}