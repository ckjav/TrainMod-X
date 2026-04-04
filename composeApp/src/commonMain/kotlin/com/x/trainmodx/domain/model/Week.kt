package com.x.trainmodx.domain.model

data class Week(
    val id: Long = 0,
    val planId: Long,
    val number: Int,
    val isDeload: Boolean = false
)