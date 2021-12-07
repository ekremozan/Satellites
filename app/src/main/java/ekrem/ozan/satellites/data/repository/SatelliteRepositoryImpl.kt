package ekrem.ozan.satellites.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ekrem.ozan.satellites.data.local.dao.SatelliteDao
import ekrem.ozan.satellites.data.local.dao.SatellitePositionDao
import ekrem.ozan.satellites.data.mapper.*
import ekrem.ozan.satellites.domain.model.*
import ekrem.ozan.satellites.domain.repository.SatelliteRepository
import ekrem.ozan.satellites.util.Constants
import ekrem.ozan.satellites.util.JsonHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.lang.reflect.Type
import javax.inject.Inject

class SatelliteRepositoryImpl @Inject constructor(
    private var satelliteDao: SatelliteDao,
    private var satellitePositionDao: SatellitePositionDao,
    private var satelliteDetailMapper: SatelliteDetailMapper,
    private var satellitePositionMapper: SatellitePositionMapper,
    private var satelliteDetailFromFileMapper: SatelliteDetailFromFileMapper,
    private var satelliteDetailDataToDetailItemMapper: SatelliteDetailDataToDetailItemMapper,
    private var positionListToPositionItemListMapper: PositionListToPositionItemListMapper,
) : SatelliteRepository {

    override fun getSatelliteDetailFromLocalDB(id: Int): Flow<SatelliteDetailData> {
        return satelliteDao.observeSatellite(id).map { data ->
            satelliteDetailMapper.toMapUiModel(data.first())
        }
    }

    override fun getSatellitePositionsFromLocalDB(id: Int): Flow<PositionList> {
        return satellitePositionDao.observeAllPositions(id).map { data ->
            satellitePositionMapper.toMapUiModel(data)
        }
    }

    override fun getCheckSatelliteIsExist(id: Int): Flow<Int> {
        return satelliteDao.observeSatelliteExist(id)
    }

    override fun getCheckSatellitePositionIsExist(id: Int): Flow<Int> {
        return satellitePositionDao.observeSatellitePositionExist(id)
    }

    override fun getSatellites(context: Context): Flow<ArrayList<SatelliteData>> = flow {
        val jsonText = JsonHelper.parseJsonFile(context, Constants.SATELLITE_LIST_FILE_NAME)
        jsonText?.let { text ->
            val type: Type = object : TypeToken<ArrayList<SatelliteData>>() {}.type
            emit(Gson().fromJson(text, type))
        }
    }

    override fun getSatelliteDetailFromFile(context: Context, id: Int): Flow<SatelliteDetailData> = flow {
        val jsonText = JsonHelper.parseJsonFile(context, Constants.SATELLITE_DETAIL_FILE_NAME)
        jsonText?.let { text ->
            val type: Type = object : TypeToken<ArrayList<SatelliteDetailFileData>>() {}.type
            val response: ArrayList<SatelliteDetailFileData> = Gson().fromJson(text, type)
            emit(satelliteDetailFromFileMapper.toMapUiModel(response.first { first -> first.id == id }))
        }
    }

    override fun getSatellitePositionsFromFile(context: Context, id: Int): Flow<PositionList> = flow {
        val jsonText = JsonHelper.parseJsonFile(context, Constants.SATELLITE_POSITION_FILE_NAME)
        jsonText?.let { text ->
            val type: Type = object : TypeToken<SatellitePositionData>() {}.type
            val response: SatellitePositionData = Gson().fromJson(text, type)
            emit(response.list.first { first -> first.id == id })
        }
    }

    override suspend fun addSatellite(satelliteDetailData: SatelliteDetailData) {
        return satelliteDao.insertSatellite(satelliteDetailDataToDetailItemMapper.toMapUiModel(satelliteDetailData))
    }

    override suspend fun addSatellitePositions(positionList: PositionList) {
        return satellitePositionDao.insertPositions(
            positionListToPositionItemListMapper.toMapUiModel(positionList)
        )
    }
}