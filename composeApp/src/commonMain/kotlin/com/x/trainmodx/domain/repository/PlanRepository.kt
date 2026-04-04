package com.x.trainmodx.domain.repository

import com.x.trainmodx.domain.model.Plan

interface PlanRepository {
    suspend fun createPlan(plan: Plan): Long
    suspend fun getActivePlan(): Plan?
    suspend fun getAllPlans(): List<Plan>
    suspend fun updatePlan(plan: Plan)
    suspend fun deletePlan(id: Long)
    suspend fun setActivePlan(id: Long)
}