package com.x.trainmodx.domain.usecase.session

import com.x.trainmodx.currentTimeMillis
import com.x.trainmodx.domain.model.Session
import com.x.trainmodx.domain.repository.SessionRepository

class StartSessionUseCase(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(trainingDayId: Long): Long {
        return sessionRepository.createSession(
            Session(
                trainingDayId = trainingDayId,
                // TODO: Replace with kotlinx-datetime in Sprint 2
                startTime = currentTimeMillis(),
                isCompleted = false
            )
        )
    }
}
