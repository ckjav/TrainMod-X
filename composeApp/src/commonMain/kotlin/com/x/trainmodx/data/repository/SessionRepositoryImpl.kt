package com.x.trainmodx.data.repository

import com.x.trainmodx.db.TrainModX
import com.x.trainmodx.domain.model.Session
import com.x.trainmodx.domain.repository.SessionRepository

class SessionRepositoryImpl(private val db: TrainModX) : SessionRepository {

    override suspend fun createSession(session: Session): Long {
        db.sessionQueries.insert(
            trainingDayId = session.trainingDayId,
            startTime = session.startTime
        )
        return db.sessionQueries.getAll().executeAsList().first().id
    }

    override suspend fun getSessionById(id: Long): Session? {
        return db.sessionQueries.getById(id).executeAsOneOrNull()?.toDomain()
    }

    override suspend fun getSessionsByDay(trainingDayId: Long): List<Session> {
        return db.sessionQueries.getByDay(trainingDayId).executeAsList().map { it.toDomain() }
    }

    override suspend fun getAllSessions(): List<Session> {
        return db.sessionQueries.getAll().executeAsList().map { it.toDomain() }
    }

    override suspend fun updateSession(session: Session) {
        db.sessionQueries.update(
            endTime = session.endTime,
            isCompleted = if (session.isCompleted) 1L else 0L,
            id = session.id
        )
    }

    private fun com.x.trainmodx.db.Session.toDomain() = Session(
        id = id,
        trainingDayId = trainingDayId,
        startTime = startTime,
        endTime = endTime,
        isCompleted = isCompleted == 1L
    )
}