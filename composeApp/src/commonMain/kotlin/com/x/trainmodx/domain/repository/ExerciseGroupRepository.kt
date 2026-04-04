package com.x.trainmodx.domain.repository
import com.x.trainmodx.domain.model.ExerciseGroup;

interface ExerciseGroupRepository {
    suspend fun createGroup(group: ExerciseGroup): Long
    suspend fun getGroupsByDay(trainingDayId: Long): List<ExerciseGroup>
    suspend fun updateGroup(group: ExerciseGroup)
    suspend fun deleteGroup(id: Long)
}