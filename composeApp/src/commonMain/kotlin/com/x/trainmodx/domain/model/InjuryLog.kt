package com.x.trainmodx.domain.model

data class InjuryLog(
    val id: Long = 0,
    val date: Long,
    val type: InjuryType,
    val bodyZone: String,
    val description: String,
    val medication: String = "",
    val dosage: String = "",
    val estimatedDays: Int = 0
)

enum class InjuryType {
    INJURY, FATIGUE, MILD_DISCOMFORT
}