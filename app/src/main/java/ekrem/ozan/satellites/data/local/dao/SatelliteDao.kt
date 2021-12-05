package ekrem.ozan.satellites.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ekrem.ozan.satellites.data.local.models.SatelliteItem

@Dao
interface SatelliteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSatellite(satellite: SatelliteItem)

    @Query("SELECT * FROM satellites where id = :satelliteId")
    fun observeSatellite(satelliteId: Int): LiveData<SatelliteItem>
}