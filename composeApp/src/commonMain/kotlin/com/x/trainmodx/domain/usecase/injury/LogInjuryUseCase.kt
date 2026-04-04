package com.x.trainmodx.domain.usecase.injury

import com.x.trainmodx.currentTimeMillis
import com.x.trainmodx.domain.model.InjuryLog
import com.x.trainmodx.domain.model.InjuryType
import com.x.trainmodx.domain.repository.InjuryLogRepository

class LogInjuryUseCase(
    private val injuryLogRepository: InjuryLogRepository
) {
    suspend operator fun invoke(
        type: InjuryType,
        bodyZone: String,
        description: String,
        medication: String = "",
        dosage: String = "",
        estimatedDays: Int = 0
    ): Long {
        return injuryLogRepository.createInjuryLog(
            InjuryLog(
                // TODO: Replace with kotlinx-datetime in Sprint 2
                date = currentTimeMillis(),
                type = type,
                bodyZone = bodyZone,
                description = description,
                medication = medication,
                dosage = dosage,
                estimatedDays = estimatedDays
            )
        )
    }
}