package ekrem.ozan.satellites.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "satellite_positions")
data class SatellitePositionItem (
    val satelliteId: Int,
    val posX: Double,
    val posY: Double,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)