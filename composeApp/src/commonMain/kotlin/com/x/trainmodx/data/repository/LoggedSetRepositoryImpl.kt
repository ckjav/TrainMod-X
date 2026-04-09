package com.x.trainmodx.data.repository

import com.x.trainmodx.db.TrainModX
import com.x.trainmodx.domain.model.LoggedSet
import com.x.trainmodx.domain.repository.LoggedSetRepository

class LoggedSetRepositoryImpl(private val db: TrainModX) : LoggedSetRepository {

    override suspend fun logSet(loggedSet: LoggedSet): Long {
        db.loggedSetQueries.insert(
            sessionId = loggedSet.sessionId,
            exerciseSetId = loggedSet.exerciseSetId,
            actualWeightKg = loggedSet.actualWeightKg,
            actualReps = loggedSet.actualReps?.toLong(),
            actualTimeSeconds = loggedSet.actualTimeSeconds?.toLong(),
            isCompleted = if (loggedSet.isCompleted) 1L else 0L,
            note = loggedSet.note
        )
        return db.loggedSetQueries.getBySession(loggedSet.sessionId).executeAsList().last().id
    }

    override suspend fun getLoggedSetsBySession(sessionId: Long): List<LoggedSet> {
        return db.loggedSetQueries.getBySession(sessionId).executeAsList().map { it.toDomain() }
    }

    override suspend fun getLoggedSetsByExercise(exerciseSetId: Long): List<LoggedSet> {
        return db.loggedSetQueries.getByExerciseSet(exerciseSetId).executeAsList().map { it.toDomain() }
    }

    override suspend fun updateLoggedSet(loggedSet: LoggedSet) {
        db.loggedSetQueries.update(
            actualWeightKg = loggedSet.actualWeightKg,
            actualReps = loggedSet.actualReps?.toLong(),
            actualTimeSeconds = loggedSet.actualTimeSeconds?.toLong(),
            isCompleted = if (loggedSet.isCompleted) 1L else 0L,
            note = loggedSet.note,
            id = loggedSet.id
        )
    }

    private fun com.x.trainmodx.db.LoggedSet.toDomain() = LoggedSet(
        id = id,
        sessionId = sessionId,
        exerciseSetId = exerciseSetId,
        actualWeightKg = actualWeightKg,
        actualReps = actualReps?.toInt(),
        actualTimeSeconds = actualTimeSeconds?.toInt(),
        isCompleted = isCompleted == 1L,
        note = note
    )
}