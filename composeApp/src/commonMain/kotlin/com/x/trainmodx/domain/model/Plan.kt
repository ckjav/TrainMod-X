package com.x.trainmodx.domain.model

data class Plan(
    val id: Long = 0,
    val name: String,
    val startDate: Long,
    val totalWeeks: Int = 5,
    val isActive: Boolean = false
)