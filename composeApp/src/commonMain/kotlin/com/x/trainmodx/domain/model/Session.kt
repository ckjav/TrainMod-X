package com.x.trainmodx.domain.model

data class Session(
    val id: Long = 0,
    val trainingDayId: Long,
    val startTime: Long,
    val endTime: Long? = null,
    val isCompleted: Boolean = false
)