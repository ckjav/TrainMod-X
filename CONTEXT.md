# GymApp - Project Context

## Meta
Side project personal - App de tracking de gym de fuerza
Solo developer, 10 horas/semana (Lun/Mie/Vie 2h, Sab 4h)
Sprints de 2 semanas, retrospectiva 30min cada sábado

## Stack
- Kotlin Multiplatform (KMP) + Compose Multiplatform
- SQLDelight 2.3.2
- Clean Architecture
- Koin (inyección de dependencias) - pendiente de configurar
- JDK 21 (Embedded Android Studio)
- Android Studio en Linux + Mac M1 para iOS
- GitHub repo con rama main

## Estructura del proyecto
- Todo vive en composeApp/ (no hay módulo shared separado)
- composeApp/src/commonMain/ → domain, data, presentation
- composeApp/src/androidMain/ → implementaciones Android
- composeApp/src/iosMain/ → implementaciones iOS

## Clean Architecture layers
composeApp/src/commonMain/kotlin/com/x/trainmodx/
├── domain/
│   ├── model/          ✅ completado
│   ├── repository/     ✅ completado
│   └── usecase/        ✅ completado
├── data/
│   ├── repository/     ⏳ pendiente
│   └── local/          ⏳ pendiente
└── presentation/
└── viewmodel/      ⏳ pendiente

## Domain Models (domain/model/)
- Plan.kt
- Week.kt
- TrainingDay.kt + enum DayOfWeek
- ExerciseGroup.kt + enum GroupType (SINGLE, BISERIES, SUPERSET, CIRCUIT)
- Exercise.kt + enum MeasureType (REPS, TIME) + enum Equipment
- ExerciseSet.kt
- Session.kt
- LoggedSet.kt
- InjuryLog.kt + enum InjuryType

## Repositories interfaces (domain/repository/)
- PlanRepository
- WeekRepository
- TrainingDayRepository
- ExerciseGroupRepository
- ExerciseRepository
- ExerciseSetRepository
- SessionRepository
- LoggedSetRepository
- InjuryLogRepository

## Use Cases (domain/usecase/)
plan/
- CreatePlanUseCase
- GetActivePlanUseCase
- CopyTrainingDayUseCase
- GetDeloadWeekSetsUseCase

session/
- StartSessionUseCase
- LogSetUseCase
- FinishSessionUseCase
- GetTodayTrainingDayUseCase

injury/
- LogInjuryUseCase
- CheckInjuryWarningUseCase + enum InjuryWarning

## TimeUtils (pendiente migrar a kotlinx-datetime en Sprint 2)
- commonMain: expect fun currentTimeMillis(): Long
- androidMain: actual fun currentTimeMillis() = System.currentTimeMillis()
- iosMain: actual fun currentTimeMillis() via NSDate
- GetTodayTrainingDayUseCase.getTodayDayOfWeek() hardcodeado a MONDAY (TODO Sprint 2)

## Decisiones de negocio
- Deload week: ceil(series / 2), solo series (reps y peso no cambian)
- Día inicio: Lunes fijo en MVP
- Día descanso: Domingo fijo en MVP
- Offline-first: 100%, sin auth, sin cloud
- Plan activo: solo uno a la vez, múltiples guardados en historial
- Peso: por serie, editable, con nota opcional
- Descanso: 3 zonas (min 45s / ideal 67s / max 90s), configurable globalmente
- Implemento: fijo por ejercicio (cambio por serie va a backlog, prioridad muy baja)
- TipoMedida: REPS o TIME (segundos) por ejercicio

## Épicas MVP
- E1: Gestión del Plan
- E2: Registro de Sesión
- E3: Historial & Progreso
- E4: Lesiones & Fatiga

## User Stories MVP
- US-01: Crear plan de entrenamiento (E1, Sprint 3)
- US-02: Configurar día de entrenamiento (E1, Sprint 3)
- US-03: Agrupar ejercicios serie/biserie/superserie/circuito (E1, Sprint 4)
- US-04: Semana de descarga automática ceil(series/2) (E1, Sprint 4)
- US-05: Iniciar sesión del día con botón Start (E2, Sprint 5)
- US-06: Ejecutar y registrar series con peso real (E2, Sprint 5)
- US-07: Cronómetro de descanso con 3 zonas (E2, Sprint 7)
- US-08: Finalizar sesión con resumen (E2, Sprint 6)
- US-09: Ver historial de sesiones (E3, Sprint 8)
- US-10: Ver progreso por ejercicio (E3, Sprint 8)
- US-11: Definir ejercicio por tiempo en segundos (E1, Sprint 4)
- US-12: Registrar peso real por serie con nota (E2, Sprint 6)
- US-13: Copiar día de entrenamiento (E1, Sprint 4)
- US-14: Registrar lesión o fatiga (E4, Sprint 9)

## Backlog
- B-01: Drag & drop reordenar ejercicios (Medium)
- B-02: Autocomplete/picklist ejercicios (Medium)
- B-03: Badge visual por tipo agrupación (Low)
- B-04: Agrupador automático por músculo (Low)
- B-05: Descarga configurable mitad reps o peso (Medium)
- B-06: Descanso adicional por fatiga + sugerencia bajar peso (Medium)
- B-07: Día inicio configurable (Medium)
- B-08: Días descanso configurables (Low)
- B-09: Múltiples sesiones por día (High)
- B-10: Soporte gym funcional HIIT/HIRT/tiempo/FC (High)
- B-11: Soporte natación (Medium)
- B-12: Integración Garmin/Polar HR (Medium)
- B-13: Split screen (Low)
- B-14: Tracking horario vs energía (Low)
- B-15: Agente IA evaluación desempeño (High)
- B-16: Nutrición (High)
- B-17: Finanzas del gym (Medium)
- B-18: Sync en la nube (High)
- B-19: Planes paralelos misma semana (High)
- B-20: Export PDF historial lesiones + rutina (Medium)
- B-21: Cambio implemento por serie (Very Low)

## Sprint actual: Sprint 1
### Completado
- [x] Crear proyecto KMP con Android Studio
- [x] Configurar estructura Clean Architecture
- [x] Crear modelos de dominio
- [x] Crear interfaces de repositorios
- [x] Crear use cases
- [x] Agregar SQLDelight 2.3.2
- [x] Configurar GitHub rama main

### Pendiente Sprint 1-2
- [x] Esquema SQL SQLDelight (archivos .sq) ✅
- [ ] Driver SQLDelight para Android
- [ ] Implementaciones de repositorios en data/
- [ ] Koin setup (inyección de dependencias)
- [ ] Agregar kotlinx-datetime (reemplazar TimeUtils TODOs)

## Comandos útiles
- Compilar: ./gradlew composeApp:compileDebugKotlinAndroid
- Generar SQLDelight: ./gradlew composeApp:generateSqlDelightInterface
- Build completo: ./gradlew build

## Nota
- Nombre de la base de datos en SQLDelight: TrainModX (no GymAppDatabase)
- Archivos .sq en: composeApp/src/commonMain/sqldelight/com/x/trainmodx/db/

