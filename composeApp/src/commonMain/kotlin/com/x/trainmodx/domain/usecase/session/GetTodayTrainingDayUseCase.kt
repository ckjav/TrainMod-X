package com.x.trainmodx.domain.usecase.session

import com.x.trainmodx.domain.model.DayOfWeek
import com.x.trainmodx.domain.model.TrainingDay
import com.x.trainmodx.domain.repository.PlanRepository
import com.x.trainmodx.domain.repository.TrainingDayRepository
import com.x.trainmodx.domain.repository.WeekRepository

class GetTodayTrainingDayUseCase(
    private val planRepository: PlanRepository,
    private val weekRepository: WeekRepository,
    private val trainingDayRepository: TrainingDayRepository
) {
    suspend operator fun invoke(): TrainingDay? {
        val activePlan = planRepository.getActivePlan() ?: return null
        val todayDayOfWeek = getTodayDayOfWeek()
        val weeks = weekRepository.getWeeksByPlan(activePlan.id)

        for (week in weeks) {
            val days = trainingDayRepository.getDaysByWeek(week.id)
            val match = days.firstOrNull { it.dayOfWeek == todayDayOfWeek }
            if (match != null) return match
        }
        return null
    }

    // TODO: Replace with kotlinx-datetime in Sprint 2
    private fun getTodayDayOfWeek(): DayOfWeek = DayOfWeek.MONDAY
    // TODO: Detect actual day of week using kotlinx-datetime in Sprint 2
    /*
    private fun getTodayDayOfWeek(): DayOfWeek {
        val calendar = java.util.Calendar.getInstance()
        return when (calendar.get(java.util.Calendar.DAY_OF_WEEK)) {
            java.util.Calendar.MONDAY -> DayOfWeek.MONDAY
            java.util.Calendar.TUESDAY -> DayOfWeek.TUESDAY
            java.util.Calendar.WEDNESDAY -> DayOfWeek.WEDNESDAY
            java.util.Calendar.THURSDAY -> DayOfWeek.THURSDAY
            java.util.Calendar.FRIDAY -> DayOfWeek.FRIDAY
            java.util.Calendar.SATURDAY -> DayOfWeek.SATURDAY
            else -> DayOfWeek.MONDAY
        }
    }
    */
}