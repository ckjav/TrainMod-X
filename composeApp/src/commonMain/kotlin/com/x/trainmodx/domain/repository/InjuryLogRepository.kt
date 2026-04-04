package com.x.trainmodx.domain.repository

import com.x.trainmodx.domain.model.InjuryLog

interface InjuryLogRepository {
    suspend fun createInjuryLog(log: InjuryLog): Long
    suspend fun getAllInjuryLogs(): List<InjuryLog>
    suspend fun getInjuryLogsByZone(bodyZone: String): List<InjuryLog>
    suspend fun getRecentInjuryLogs(fromDate: Long): List<InjuryLog>
}