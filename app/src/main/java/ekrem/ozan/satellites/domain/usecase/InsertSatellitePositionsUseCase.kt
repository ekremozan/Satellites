package ekrem.ozan.satellites.domain.usecase

import ekrem.ozan.satellites.base.domain.BaseUseCase
import ekrem.ozan.satellites.domain.model.PositionList
import ekrem.ozan.satellites.domain.repository.SatelliteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertSatellitePositionsUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository
) : BaseUseCase<PositionList, Unit>() {

    override fun execute(parameters: PositionList): Flow<Unit> = flow {
        emit(satelliteRepository.addSatellitePositions(parameters))
    }
}
