package ekrem.ozan.satellites.domain.repository

import android.content.Context
import ekrem.ozan.satellites.domain.model.PositionList
import ekrem.ozan.satellites.domain.model.SatelliteData
import ekrem.ozan.satellites.domain.model.SatelliteDetailData
import kotlinx.coroutines.flow.Flow

interface SatelliteRepository {
    fun getSatellites(context: Context): Flow<ArrayList<SatelliteData>>
    fun getSatelliteDetailFromLocalDB(id: Int): Flow<SatelliteDetailData>
    fun getSatellitePositionsFromLocalDB(id: Int): Flow<PositionList>
    fun getCheckSatelliteIsExist(id: Int): Flow<Int>
    fun getCheckSatellitePositionIsExist(id: Int): Flow<Int>
    fun getSatelliteDetailFromFile(context: Context, id: Int): Flow<SatelliteDetailData>
    fun getSatellitePositionsFromFile(context: Context, id: Int): Flow<PositionList>
    suspend fun addSatellite(satelliteDetailData: SatelliteDetailData)
    suspend fun addSatellitePositions(positionList: PositionList)
}