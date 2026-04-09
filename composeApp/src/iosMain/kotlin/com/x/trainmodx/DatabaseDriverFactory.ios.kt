import app.cash.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        // TODO: Implement iOS driver with NativeSqliteDriver in Sprint 2
        throw NotImplementedError("iOS driver not implemented yet")
    }
}