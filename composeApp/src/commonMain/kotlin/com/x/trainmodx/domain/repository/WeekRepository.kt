package com.x.trainmodx.domain.repository

import com.x.trainmodx.domain.model.Week

interface WeekRepository {
    suspend fun createWeek(week: Week): Long
    suspend fun getWeeksByPlan(planId: Long): List<Week>
    suspend fun updateWeek(week: Week)
    suspend fun deleteWeek(id: Long)
}