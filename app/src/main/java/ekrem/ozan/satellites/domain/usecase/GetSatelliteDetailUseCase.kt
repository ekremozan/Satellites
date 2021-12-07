package ekrem.ozan.satellites.domain.usecase

import android.content.Context
import ekrem.ozan.satellites.base.domain.BaseUseCase
import ekrem.ozan.satellites.domain.model.SatelliteDetailData
import ekrem.ozan.satellites.domain.repository.SatelliteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSatelliteDetailUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository
) : BaseUseCase<GetSatelliteDetailUseCase.Params, SatelliteDetailData>() {

    override fun execute(parameters: Params): Flow<SatelliteDetailData> {
        return when (parameters.isLocal) {
            true -> satelliteRepository.getSatelliteDetailFromLocalDB(parameters.id)
            false -> parameters.context?.let { satelliteRepository.getSatelliteDetailFromFile(it, parameters.id) } ?: flow { }
        }
    }

    data class Params(
        val id: Int,
        val isLocal: Boolean,
        val context: Context? = null
    )
}