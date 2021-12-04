package ekrem.ozan.satellites.domain.repository

import android.content.Context
import ekrem.ozan.satellites.domain.model.SatelliteData
import kotlinx.coroutines.flow.Flow

interface SatelliteRepository {
    fun getSatellites(context: Context): Flow<ArrayList<SatelliteData>>
}