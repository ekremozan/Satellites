package ekrem.ozan.satellites.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ekrem.ozan.satellites.data.local.models.SatelliteDetailItem
import kotlinx.coroutines.flow.Flow

@Dao
interface SatelliteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSatellite(satelliteDetail: SatelliteDetailItem)

    @Query("SELECT * FROM satellites where id = :id")
    fun observeSatellite(id: Int): Flow<List<SatelliteDetailItem>>

    @Query("SELECT Count(id) FROM satellites where id = :id")
    fun observeSatelliteExist(id: Int): Flow<Int>
}