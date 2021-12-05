package ekrem.ozan.satellites.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "satellite_positions")
data class SatellitePositionItem (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var satelliteId: Int,
    var posX: Double,
    var posY: Double
)