package com.x.trainmodx.domain.repository

import com.x.trainmodx.domain.model.LoggedSet

interface LoggedSetRepository {
    suspend fun logSet(loggedSet: LoggedSet): Long
    suspend fun getLoggedSetsBySession(sessionId: Long): List<LoggedSet>
    suspend fun getLoggedSetsByExercise(exerciseSetId: Long): List<LoggedSet>
    suspend fun updateLoggedSet(loggedSet: LoggedSet)
}