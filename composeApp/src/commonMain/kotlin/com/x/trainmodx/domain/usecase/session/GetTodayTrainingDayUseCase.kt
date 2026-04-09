package com.x.trainmodx.domain.usecase.session

import com.x.trainmodx.domain.model.DayOfWeek
import com.x.trainmodx.domain.model.TrainingDay
import com.x.trainmodx.domain.repository.PlanRepository
import com.x.trainmodx.domain.repository.TrainingDayRepository
import com.x.trainmodx.domain.repository.WeekRepository
import com.x.trainmodx.currentDayOfWeek

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

    private fun getTodayDayOfWeek(): DayOfWeek = currentDayOfWeek()

}