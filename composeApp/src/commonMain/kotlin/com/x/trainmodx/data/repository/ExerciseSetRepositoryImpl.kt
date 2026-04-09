package com.x.trainmodx.data.repository

import com.x.trainmodx.db.TrainModX
import com.x.trainmodx.domain.model.ExerciseSet
import com.x.trainmodx.domain.repository.ExerciseSetRepository

class ExerciseSetRepositoryImpl(private val db: TrainModX) : ExerciseSetRepository {

    override suspend fun createSet(set: ExerciseSet): Long {
        db.exerciseSetQueries.insert(
            exerciseId = set.exerciseId,
            number = set.number.toLong(),
            targetReps = set.targetReps?.toLong(),
            targetTimeSeconds = set.targetTimeSeconds?.toLong(),
            targetWeightKg = set.targetWeightKg
        )
        return db.exerciseSetQueries.getByExercise(set.exerciseId).executeAsList().last().id
    }

    override suspend fun getSetsByExercise(exerciseId: Long): List<ExerciseSet> {
        return db.exerciseSetQueries.getByExercise(exerciseId).executeAsList().map { it.toDomain() }
    }

    override suspend fun updateSet(set: ExerciseSet) {
        db.exerciseSetQueries.update(
            number = set.number.toLong(),
            targetReps = set.targetReps?.toLong(),
            targetTimeSeconds = set.targetTimeSeconds?.toLong(),
            targetWeightKg = set.targetWeightKg,
            id = set.id
        )
    }

    override suspend fun deleteSet(id: Long) {
        db.exerciseSetQueries.deleteById(id)
    }

    private fun com.x.trainmodx.db.ExerciseSet.toDomain() = ExerciseSet(
        id = id,
        exerciseId = exerciseId,
        number = number.toInt(),
        targetReps = targetReps?.toInt(),
        targetTimeSeconds = targetTimeSeconds?.toInt(),
        targetWeightKg = targetWeightKg
    )
}