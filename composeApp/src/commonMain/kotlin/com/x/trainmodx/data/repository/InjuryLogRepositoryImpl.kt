package com.x.trainmodx.data.repository

import com.x.trainmodx.db.TrainModX
import com.x.trainmodx.domain.model.InjuryLog
import com.x.trainmodx.domain.model.InjuryType
import com.x.trainmodx.domain.repository.InjuryLogRepository

class InjuryLogRepositoryImpl(private val db: TrainModX) : InjuryLogRepository {

    override suspend fun createInjuryLog(log: InjuryLog): Long {
        db.injuryLogQueries.insert(
            date = log.date,
            type = log.type.name,
            bodyZone = log.bodyZone,
            description = log.description,
            medication = log.medication,
            dosage = log.dosage,
            estimatedDays = log.estimatedDays.toLong()
        )
        return db.injuryLogQueries.getAll().executeAsList().first().id
    }

    override suspend fun getAllInjuryLogs(): List<InjuryLog> {
        return db.injuryLogQueries.getAll().executeAsList().map { it.toDomain() }
    }

    override suspend fun getInjuryLogsByZone(bodyZone: String): List<InjuryLog> {
        return db.injuryLogQueries.getByZone(bodyZone).executeAsList().map { it.toDomain() }
    }

    override suspend fun getRecentInjuryLogs(fromDate: Long): List<InjuryLog> {
        return db.injuryLogQueries.getFromDate(fromDate).executeAsList().map { it.toDomain() }
    }

    private fun com.x.trainmodx.db.InjuryLog.toDomain() = InjuryLog(
        id = id,
        date = date,
        type = InjuryType.valueOf(type),
        bodyZone = bodyZone,
        description = description,
        medication = medication,
        dosage = dosage,
        estimatedDays = estimatedDays.toInt()
    )
}