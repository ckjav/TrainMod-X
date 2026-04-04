package com.x.trainmodx.domain.model

data class ExerciseGroup(
    val id: Long = 0,
    val trainingDayId: Long,
    val type: GroupType,
    val order: Int,
    val restMinSeconds: Int = 45,
    val restIdealSeconds: Int = 67,
    val restMaxSeconds: Int = 90
)

enum class GroupType {
    SINGLE, BISERIES, SUPERSET, CIRCUIT
}