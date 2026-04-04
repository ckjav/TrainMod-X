package com.x.trainmodx.domain.usecase.plan

class GetDeloadWeekSetsUseCase {
    operator fun invoke(originalSets: Int): Int {
        return Math.ceil(originalSets / 2.0).toInt()
    }
}