package com.x.trainmodx.data.repository

import com.x.trainmodx.db.TrainModX
import com.x.trainmodx.domain.model.Equipment
import com.x.trainmodx.domain.model.Exercise
import com.x.trainmodx.domain.model.MeasureType
import com.x.trainmodx.domain.repository.ExerciseRepository

class ExerciseRepositoryImpl(private val db: TrainModX) : ExerciseRepository {

    override suspend fun createExercise(exercise: Exercise): Long {
        db.exerciseQueries.insert(
            groupId = exercise.groupId,
            name = exercise.name,
            orderIndex = exercise.order.toLong(),
            measureType = exercise.measureType.name,
            equipment = exercise.equipment.name
        )
        return db.exerciseQueries.getByGroup(exercise.groupId).executeAsList().last().id
    }

    override suspend fun getExercisesByGroup(groupId: Long): List<Exercise> {
        return db.exerciseQueries.getByGroup(groupId).executeAsList().map { it.toDomain() }
    }

    override suspend fun updateExercise(exercise: Exercise) {
        db.exerciseQueries.update(
            name = exercise.name,
            orderIndex = exercise.order.toLong(),
            measureType = exercise.measureType.name,
            equipment = exercise.equipment.name,
            id = exercise.id
        )
    }

    override suspend fun deleteExercise(id: Long) {
        db.exerciseQueries.deleteById(id)
    }

    private fun com.x.trainmodx.db.Exercise.toDomain() = Exercise(
        id = id,
        groupId = groupId,
        name = name,
        order = orderIndex.toInt(),
        measureType = MeasureType.valueOf(measureType),
        equipment = Equipment.valueOf(equipment)
    )
}