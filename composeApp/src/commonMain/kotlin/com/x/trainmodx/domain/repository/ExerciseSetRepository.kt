package com.x.trainmodx.domain.repository

import com.x.trainmodx.domain.model.ExerciseSet

interface ExerciseSetRepository {
    suspend fun createSet(set: ExerciseSet): Long
    suspend fun getSetsByExercise(exerciseId: Long): List<ExerciseSet>
    suspend fun updateSet(set: ExerciseSet)
    suspend fun deleteSet(id: Long)
}