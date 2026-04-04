package com.x.trainmodx.domain.usecase.session

import com.x.trainmodx.domain.model.LoggedSet
import com.x.trainmodx.domain.repository.LoggedSetRepository

class LogSetUseCase(
    private val loggedSetRepository: LoggedSetRepository
) {
    suspend operator fun invoke(
        sessionId: Long,
        exerciseSetId: Long,
        actualWeightKg: Double?,
        actualReps: Int?,
        actualTimeSeconds: Int?,
        note: String = ""
    ): Long {
        return loggedSetRepository.logSet(
            LoggedSet(
                sessionId = sessionId,
                exerciseSetId = exerciseSetId,
                actualWeightKg = actualWeightKg,
                actualReps = actualReps,
                actualTimeSeconds = actualTimeSeconds,
                isCompleted = true,
                note = note
            )
        )
    }
}