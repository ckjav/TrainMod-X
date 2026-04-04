package com.x.trainmodx.domain.repository

import com.x.trainmodx.domain.model.Exercise

interface ExerciseRepository {
    suspend fun createExercise(exercise: Exercise): Long
    suspend fun getExercisesByGroup(groupId: Long): List<Exercise>
    suspend fun updateExercise(exercise: Exercise)
    suspend fun deleteExercise(id: Long)
}