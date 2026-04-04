package com.x.trainmodx.domain.usecase.session

import com.x.trainmodx.currentTimeMillis
import com.x.trainmodx.domain.repository.SessionRepository

class FinishSessionUseCase(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(sessionId: Long) {
        val session = sessionRepository.getSessionById(sessionId) ?: return
        sessionRepository.updateSession(
            session.copy(
                // TODO: Replace with kotlinx-datetime in Sprint 2
                endTime = currentTimeMillis(),
                isCompleted = true
            )
        )
    }
}

