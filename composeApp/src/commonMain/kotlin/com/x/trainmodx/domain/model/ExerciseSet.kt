package com.x.trainmodx.domain.model

data class ExerciseSet(
    val id: Long = 0,
    val exerciseId: Long,
    val number: Int,
    val targetReps: Int? = null,
    val targetTimeSeconds: Int? = null,
    val targetWeightKg: Double? = null
)