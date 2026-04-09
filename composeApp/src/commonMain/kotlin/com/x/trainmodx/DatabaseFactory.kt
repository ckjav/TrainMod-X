import com.x.trainmodx.db.TrainModX

class DatabaseFactory(private val driverFactory: DatabaseDriverFactory) {
    fun createDatabase(): TrainModX {
        return TrainModX(driverFactory.createDriver())
    }
}