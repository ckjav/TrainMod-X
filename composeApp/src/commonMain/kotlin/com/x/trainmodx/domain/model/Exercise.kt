package com.x.trainmodx.domain.model

data class Exercise(
    val id: Long = 0,
    val groupId: Long,
    val name: String,
    val order: Int,
    val measureType: MeasureType,
    val equipment: Equipment = Equipment.BODYWEIGHT,
    val sets: List<ExerciseSet> = emptyList()
)

enum class MeasureType {
    REPS, TIME
}

enum class Equipment {
    BARBELL, DUMBBELL, MACHINE, CABLE, BODYWEIGHT
}