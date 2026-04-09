package com.x.trainmodx

import DatabaseDriverFactory
import com.x.trainmodx.data.local.DatabaseFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val androidModule = module {
    single { DatabaseDriverFactory(androidContext()) }
    single { DatabaseFactory(get()).createDatabase() }
}