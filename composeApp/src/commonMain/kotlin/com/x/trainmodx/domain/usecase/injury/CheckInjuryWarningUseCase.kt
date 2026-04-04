package com.x.trainmodx.domain.usecase.injury

import com.x.trainmodx.currentTimeMillis
import com.x.trainmodx.domain.repository.InjuryLogRepository

class CheckInjuryWarningUseCase(
    private val injuryLogRepository: InjuryLogRepository
) {
    suspend operator fun invoke(bodyZone: String): InjuryWarning {
        //
        val sixMonthsAgo = currentTimeMillis() - (6 * 30 * 24 * 60 * 60 * 1000L)
        val recentLogs = injuryLogRepository.getInjuryLogsByZone(bodyZone)
            .filter { it.date >= sixMonthsAgo }

        return when {
            recentLogs.size >= 3 -> InjuryWarning.PATTERN_DETECTED
            recentLogs.isNotEmpty() -> InjuryWarning.RECENT_INJURY
            else -> InjuryWarning.NONE
        }
    }
}

enum class InjuryWarning {
    NONE,
    RECENT_INJURY,
    PATTERN_DETECTED
}