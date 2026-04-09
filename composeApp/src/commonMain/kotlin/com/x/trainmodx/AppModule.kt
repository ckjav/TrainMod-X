package com.x.trainmodx

import DatabaseDriverFactory
import com.x.trainmodx.data.local.DatabaseFactory
import com.x.trainmodx.data.repository.ExerciseGroupRepositoryImpl
import com.x.trainmodx.data.repository.ExerciseRepositoryImpl
import com.x.trainmodx.data.repository.ExerciseSetRepositoryImpl
import com.x.trainmodx.data.repository.InjuryLogRepositoryImpl
import com.x.trainmodx.data.repository.LoggedSetRepositoryImpl
import com.x.trainmodx.data.repository.PlanRepositoryImpl
import com.x.trainmodx.data.repository.SessionRepositoryImpl
import com.x.trainmodx.data.repository.TrainingDayRepositoryImpl
import com.x.trainmodx.data.repository.WeekRepositoryImpl
import com.x.trainmodx.domain.repository.*
import com.x.trainmodx.domain.usecase.injury.CheckInjuryWarningUseCase
import com.x.trainmodx.domain.usecase.injury.LogInjuryUseCase
import com.x.trainmodx.domain.usecase.plan.CopyTrainingDayUseCase
import com.x.trainmodx.domain.usecase.plan.CreatePlanUseCase
import com.x.trainmodx.domain.usecase.plan.GetActivePlanUseCase
import com.x.trainmodx.domain.usecase.plan.GetDeloadWeekSetsUseCase
import com.x.trainmodx.domain.usecase.session.FinishSessionUseCase
import com.x.trainmodx.domain.usecase.session.GetTodayTrainingDayUseCase
import com.x.trainmodx.domain.usecase.session.LogSetUseCase
import com.x.trainmodx.domain.usecase.session.StartSessionUseCase
import org.koin.dsl.module

val appModule = module {

    // Database
    // single { DatabaseDriverFactory(get()) }
    // single { com.x.trainmodx.data.local.DatabaseFactory(get()).createDatabase() }
    single { DatabaseFactory(get()).createDatabase() }

    // Repositories
    single<PlanRepository> { PlanRepositoryImpl(get()) }
    single<WeekRepository> { WeekRepositoryImpl(get()) }
    single<TrainingDayRepository> { TrainingDayRepositoryImpl(get()) }
    single<ExerciseGroupRepository> { ExerciseGroupRepositoryImpl(get()) }
    single<ExerciseRepository> { ExerciseRepositoryImpl(get()) }
    single<ExerciseSetRepository> { ExerciseSetRepositoryImpl(get()) }
    single<SessionRepository> { SessionRepositoryImpl(get()) }
    single<LoggedSetRepository> { LoggedSetRepositoryImpl(get()) }
    single<InjuryLogRepository> { InjuryLogRepositoryImpl(get()) }

    // Use Cases - Plan
    factory { CreatePlanUseCase(get(), get()) }
    factory { GetActivePlanUseCase(get()) }
    factory { CopyTrainingDayUseCase(get(), get(), get(), get()) }
    factory { GetDeloadWeekSetsUseCase() }

    // Use Cases - Session
    factory { StartSessionUseCase(get()) }
    factory { LogSetUseCase(get()) }
    factory { FinishSessionUseCase(get()) }
    factory { GetTodayTrainingDayUseCase(get(), get(), get()) }

    // Use Cases - Injury
    factory { LogInjuryUseCase(get()) }
    factory { CheckInjuryWarningUseCase(get()) }
}