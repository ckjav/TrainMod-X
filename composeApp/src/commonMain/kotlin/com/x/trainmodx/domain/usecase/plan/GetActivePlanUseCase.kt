package com.x.trainmodx.domain.usecase.plan

import com.x.trainmodx.domain.model.Plan
import com.x.trainmodx.domain.repository.PlanRepository

class GetActivePlanUseCase(
    private val planRepository: PlanRepository
) {
    suspend operator fun invoke(): Plan? = planRepository.getActivePlan()
}