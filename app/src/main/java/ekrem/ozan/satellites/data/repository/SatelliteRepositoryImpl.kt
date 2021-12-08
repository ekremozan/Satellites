package ekrem.ozan.satellites.data.repository

import android.content.Context
import ekrem.ozan.satellites.data.local.dao.SatelliteDao
import ekrem.ozan.satellites.data.mapper.SatelliteDetailDataToDetailItemMapper
import ekrem.ozan.satellites.data.mapper.SatelliteDetailFromFileMapper
import ekrem.ozan.satellites.data.mapper.SatelliteDetailMapper
import ekrem.ozan.satellites.domain.model.SatelliteData
import ekrem.ozan.satellites.domain.model.SatelliteDetailData
import ekrem.ozan.satellites.domain.model.SatelliteDetailFileData
import ekrem.ozan.satellites.domain.repository.SatelliteRepository
import ekrem.ozan.satellites.util.Constants
import ekrem.ozan.satellites.util.JsonHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SatelliteRepositoryImpl @Inject constructor(
    private var satelliteDao: SatelliteDao,
    private var satelliteDetailMapper: SatelliteDetailMapper,
    private var satelliteDetailFromFileMapper: SatelliteDetailFromFileMapper,
    private var satelliteDetailDataToDetailItemMapper: SatelliteDetailDataToDetailItemMapper,
) : SatelliteRepository {

    override fun getSatelliteDetailFromLocalDB(id: Int): Flow<SatelliteDetailData> {
        return satelliteDao.observeSatellite(id).map { data ->
            satelliteDetailMapper.toMapUiModel(data.first())
        }
    }

    override fun getCheckSatelliteIsExist(id: Int): Flow<Int> {
        return satelliteDao.observeSatelliteExist(id)
    }

    override fun getSatellites(context: Context): Flow<ArrayList<SatelliteData>> = flow {
        val jsonText = JsonHelper.parseJsonFile(context, Constants.SATELLITE_LIST_FILE_NAME)
        jsonText?.let { emit(JsonHelper.fromJson(it)) }
    }

    override fun getSatelliteDetailFromFile(context: Context, id: Int): Flow<SatelliteDetailData> = flow {
        val jsonText = JsonHelper.parseJsonFile(context, Constants.SATELLITE_DETAIL_FILE_NAME)
        jsonText?.let { text ->
            val response: ArrayList<SatelliteDetailFileData> = JsonHelper.fromJson(text)
            emit(satelliteDetailFromFileMapper.toMapUiModel(response.first { first -> first.id == id }))
        }
    }

    override suspend fun addSatellite(satelliteDetailData: SatelliteDetailData) {
        return satelliteDao.insertSatellite(satelliteDetailDataToDetailItemMapper.toMapUiModel(satelliteDetailData))
    }
}