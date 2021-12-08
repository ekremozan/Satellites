package ekrem.ozan.satellites.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ekrem.ozan.satellites.data.local.dao.SatellitePositionDao
import ekrem.ozan.satellites.data.mapper.PositionListToPositionItemListMapper
import ekrem.ozan.satellites.data.mapper.SatellitePositionMapper
import ekrem.ozan.satellites.domain.model.PositionList
import ekrem.ozan.satellites.domain.model.SatellitePositionData
import ekrem.ozan.satellites.domain.repository.SatellitePositionsRepository
import ekrem.ozan.satellites.util.Constants
import ekrem.ozan.satellites.util.JsonHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.lang.reflect.Type
import javax.inject.Inject

class SatellitePositionsRepositoryImpl @Inject constructor(
    private var satellitePositionDao: SatellitePositionDao,
    private var satellitePositionMapper: SatellitePositionMapper,
    private var positionListToPositionItemListMapper: PositionListToPositionItemListMapper,
) : SatellitePositionsRepository {

    override suspend fun addSatellitePositions(positionList: PositionList) {
        return satellitePositionDao.insertPositions(
            positionListToPositionItemListMapper.toMapUiModel(positionList)
        )
    }

    override fun getSatellitePositionsFromLocalDB(id: Int): Flow<PositionList> {
        return satellitePositionDao.observeAllPositions(id).map { data ->
            satellitePositionMapper.toMapUiModel(data)
        }
    }

    override fun getSatellitePositionsFromFile(context: Context, id: Int): Flow<PositionList> = flow {
        val jsonText = JsonHelper.parseJsonFile(context, Constants.SATELLITE_POSITION_FILE_NAME)
        jsonText?.let { text ->
            val response: SatellitePositionData =  JsonHelper.fromJson(text)
            emit(response.list.first { first -> first.id == id })
        }
    }

    override fun getCheckSatellitePositionIsExist(id: Int): Flow<Int> {
        return satellitePositionDao.observeSatellitePositionExist(id)
    }
}