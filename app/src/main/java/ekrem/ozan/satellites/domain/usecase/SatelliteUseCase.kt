package ekrem.ozan.satellites.domain.usecase

import android.content.Context
import ekrem.ozan.satellites.base.domain.BaseUseCase
import ekrem.ozan.satellites.data.local.dao.SatelliteDao
import ekrem.ozan.satellites.data.local.models.SatelliteItem
import ekrem.ozan.satellites.domain.model.SatelliteData
import ekrem.ozan.satellites.domain.repository.SatelliteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SatelliteUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository,
    private val satelliteDao: SatelliteDao
) : BaseUseCase<Context, ArrayList<SatelliteData>>() {

    override fun execute(parameters: Context): Flow<ArrayList<SatelliteData>> {
      return  satelliteRepository.getSatellites(parameters)
    }
}