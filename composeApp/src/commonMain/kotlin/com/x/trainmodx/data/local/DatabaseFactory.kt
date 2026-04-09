package com.x.trainmodx.data.local

import DatabaseDriverFactory
import com.x.trainmodx.db.TrainModX

class DatabaseFactory(private val driverFactory: DatabaseDriverFactory) {
    fun createDatabase(): TrainModX {
        return TrainModX.Companion(driverFactory.createDriver())
    }
}