package com.x.trainmodx.domain.model

data class LoggedSet(
    val id: Long = 0,
    val sessionId: Long,
    val exerciseSetId: Long,
    val actualWeightKg: Double? = null,
    val actualReps: Int? = null,
    val actualTimeSeconds: Int? = null,
    val isCompleted: Boolean = false,
    val note: String = ""
)