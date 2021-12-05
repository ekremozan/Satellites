package ekrem.ozan.satellites.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ekrem.ozan.satellites.data.local.dao.SatelliteDao
import ekrem.ozan.satellites.data.local.dao.SatellitePositionDao
import ekrem.ozan.satellites.data.local.models.SatelliteItem
import ekrem.ozan.satellites.data.local.models.SatellitePositionItem

@Database(
    entities = [
        SatelliteItem::class,
        SatellitePositionItem::class],
    version = 1
)
abstract class SatelliteDatabase : RoomDatabase() {
    abstract fun satelliteDao(): SatelliteDao
    abstract fun satellitePositionDao(): SatellitePositionDao
}