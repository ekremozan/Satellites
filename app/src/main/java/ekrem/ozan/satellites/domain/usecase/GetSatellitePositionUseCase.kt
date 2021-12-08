package ekrem.ozan.satellites.domain.usecase

import android.content.Context
import ekrem.ozan.satellites.base.domain.BaseUseCase
import ekrem.ozan.satellites.domain.model.PositionList
import ekrem.ozan.satellites.domain.repository.SatellitePositionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSatellitePositionUseCase @Inject constructor(
    private val satellitePositionsRepository: SatellitePositionsRepository
) : BaseUseCase<GetSatellitePositionUseCase.Params, PositionList>() {

    override fun execute(parameters: Params): Flow<PositionList> {
        return when (parameters.isLocal) {
            true -> satellitePositionsRepository.getSatellitePositionsFromLocalDB(parameters.id)
            false -> parameters.context?.let { satellitePositionsRepository.getSatellitePositionsFromFile(it, parameters.id) } ?: flow { }
        }
    }

    data class Params(
        val id: Int,
        val isLocal: Boolean,
        val context: Context? = null
    )
}