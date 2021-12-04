package ekrem.ozan.satellites.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ekrem.ozan.satellites.domain.model.SatelliteData
import ekrem.ozan.satellites.domain.repository.SatelliteRepository
import ekrem.ozan.satellites.util.Constants
import ekrem.ozan.satellites.util.JsonHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.reflect.Type
import javax.inject.Inject

class SatelliteRepositoryImpl @Inject constructor() : SatelliteRepository {
    override fun getSatellites(context: Context): Flow<ArrayList<SatelliteData>> = flow {
        val jsonText = JsonHelper.parseJsonFile(context, Constants.SATELLITE_LIST_FILE_NAME)
        jsonText?.let { text ->
            val type: Type = object : TypeToken<ArrayList<SatelliteData>>() {}.type
            emit(Gson().fromJson(text, type))
        }
    }
}