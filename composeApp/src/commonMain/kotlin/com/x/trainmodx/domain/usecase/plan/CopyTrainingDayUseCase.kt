package com.x.trainmodx.domain.usecase.plan

import com.x.trainmodx.domain.model.DayOfWeek
import com.x.trainmodx.domain.repository.ExerciseGroupRepository
import com.x.trainmodx.domain.repository.ExerciseRepository
import com.x.trainmodx.domain.repository.ExerciseSetRepository
import com.x.trainmodx.domain.repository.TrainingDayRepository

class CopyTrainingDayUseCase(
    private val trainingDayRepository: TrainingDayRepository,
    private val exerciseGroupRepository: ExerciseGroupRepository,
    private val exerciseRepository: ExerciseRepository,
    private val exerciseSetRepository: ExerciseSetRepository
) {
    suspend operator fun invoke(
        sourceDayId: Long,
        targetWeekId: Long,
        targetDayOfWeek: DayOfWeek
    ): Long {
        return trainingDayRepository.copyTrainingDay(
            sourceDayId = sourceDayId,
            targetWeekId = targetWeekId,
            targetDayOfWeek = targetDayOfWeek
        )
    }
}