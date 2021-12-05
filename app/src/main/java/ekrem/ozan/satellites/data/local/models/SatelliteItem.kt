package ekrem.ozan.satellites.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "satellites")
data class SatelliteItem(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var name: String,
    var costPerLaunch: Int,
    var firstFlight: String,
    var height: Int,
    var mass: Int
)