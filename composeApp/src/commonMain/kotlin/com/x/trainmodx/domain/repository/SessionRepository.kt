package com.x.trainmodx.domain.repository

import com.x.trainmodx.domain.model.Session

interface SessionRepository {
    suspend fun createSession(session: Session): Long
    suspend fun getSessionById(id: Long): Session?
    suspend fun getSessionsByDay(trainingDayId: Long): List<Session>
    suspend fun getAllSessions(): List<Session>
    suspend fun updateSession(session: Session)
}