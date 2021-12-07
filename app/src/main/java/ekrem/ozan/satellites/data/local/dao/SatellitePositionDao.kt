package ekrem.ozan.satellites.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ekrem.ozan.satellites.data.local.models.SatellitePositionItem
import kotlinx.coroutines.flow.Flow

@Dao
interface SatellitePositionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPositions(positionItem: List<SatellitePositionItem>)

    @Query("SELECT * FROM satellite_positions where satelliteId = :satelliteId")
    fun observeAllPositions(satelliteId: Int): Flow<List<SatellitePositionItem>>

    @Query("SELECT Count(*) FROM satellite_positions where satelliteId = :satelliteId")
    fun observeSatellitePositionExist(satelliteId: Int): Flow<Int>
}