package ekrem.ozan.satellites.domain.usecase

import ekrem.ozan.satellites.base.domain.BaseUseCase
import ekrem.ozan.satellites.domain.model.SatelliteDetailData
import ekrem.ozan.satellites.domain.repository.SatelliteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertSatelliteUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository
) : BaseUseCase<SatelliteDetailData, Unit>() {

    override fun execute(parameters: SatelliteDetailData): Flow<Unit> = flow {
        emit(satelliteRepository.addSatellite(parameters))
    }
}
