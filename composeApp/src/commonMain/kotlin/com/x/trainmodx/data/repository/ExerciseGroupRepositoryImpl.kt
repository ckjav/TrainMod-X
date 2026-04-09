package com.x.trainmodx.data.repository

import com.x.trainmodx.db.TrainModX
import com.x.trainmodx.domain.model.ExerciseGroup
import com.x.trainmodx.domain.model.GroupType
import com.x.trainmodx.domain.repository.ExerciseGroupRepository

class ExerciseGroupRepositoryImpl(private val db: TrainModX) : ExerciseGroupRepository {

    override suspend fun createGroup(group: ExerciseGroup): Long {
        db.exerciseGroupQueries.insert(
            trainingDayId = group.trainingDayId,
            type = group.type.name,
            orderIndex = group.order.toLong(),
            restMinSeconds = group.restMinSeconds.toLong(),
            restIdealSeconds = group.restIdealSeconds.toLong(),
            restMaxSeconds = group.restMaxSeconds.toLong()
        )
        return db.exerciseGroupQueries.getByDay(group.trainingDayId).executeAsList().last().id
    }

    override suspend fun getGroupsByDay(trainingDayId: Long): List<ExerciseGroup> {
        return db.exerciseGroupQueries.getByDay(trainingDayId).executeAsList().map { it.toDomain() }
    }

    override suspend fun updateGroup(group: ExerciseGroup) {
        db.exerciseGroupQueries.update(
            type = group.type.name,
            orderIndex = group.order.toLong(),
            restMinSeconds = group.restMinSeconds.toLong(),
            restIdealSeconds = group.restIdealSeconds.toLong(),
            restMaxSeconds = group.restMaxSeconds.toLong(),
            id = group.id
        )
    }

    override suspend fun deleteGroup(id: Long) {
        db.exerciseGroupQueries.deleteById(id)
    }

    private fun com.x.trainmodx.db.ExerciseGroup.toDomain() = ExerciseGroup(
        id = id,
        trainingDayId = trainingDayId,
        type = GroupType.valueOf(type),
        order = orderIndex.toInt(),
        restMinSeconds = restMinSeconds.toInt(),
        restIdealSeconds = restIdealSeconds.toInt(),
        restMaxSeconds = restMaxSeconds.toInt()
    )
}