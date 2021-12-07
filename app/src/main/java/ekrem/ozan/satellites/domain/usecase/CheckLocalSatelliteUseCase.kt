package ekrem.ozan.satellites.domain.usecase

import ekrem.ozan.satellites.base.domain.BaseUseCase
import ekrem.ozan.satellites.domain.repository.SatelliteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckLocalSatelliteUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository
) : BaseUseCase<Int, Boolean>() {
    override fun execute(parameters: Int): Flow<Boolean> {
        return if (parameters == 0) flow { emit(false) } else {
            val satellite = satelliteRepository.getCheckSatelliteIsExist(parameters)
            val satellitePositions = satelliteRepository.getCheckSatellitePositionIsExist(parameters)

            satellite.combine(satellitePositions) { satelliteCount, satellitePositionCount -> satelliteCount + satellitePositionCount > 1 }
        }
    }
}