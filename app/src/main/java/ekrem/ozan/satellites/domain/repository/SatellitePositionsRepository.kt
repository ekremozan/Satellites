package ekrem.ozan.satellites.domain.repository

import android.content.Context
import ekrem.ozan.satellites.domain.model.PositionList
import kotlinx.coroutines.flow.Flow

interface SatellitePositionsRepository {
    fun getSatellitePositionsFromLocalDB(id: Int): Flow<PositionList>
    fun getCheckSatellitePositionIsExist(id: Int): Flow<Int>
    fun getSatellitePositionsFromFile(context: Context, id: Int): Flow<PositionList>
    suspend fun addSatellitePositions(positionList: PositionList)
}