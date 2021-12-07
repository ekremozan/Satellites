package ekrem.ozan.satellites.data.mapper

import ekrem.ozan.satellites.base.extensions.orZero
import ekrem.ozan.satellites.data.Mapper
import ekrem.ozan.satellites.domain.model.SatelliteDetailData
import ekrem.ozan.satellites.domain.model.SatelliteDetailFileData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SatelliteDetailFromFileMapper @Inject constructor() : Mapper<SatelliteDetailFileData?, SatelliteDetailData> {
    override fun toMapUiModel(model: SatelliteDetailFileData?): SatelliteDetailData {
        return SatelliteDetailData(
            id = model?.id ?: 0,
            costPerLaunch = model?.cost_per_launch.orZero(),
            firstFlight = model?.first_flight.orEmpty(),
            height = model?.height.orZero(),
            mass = model?.mass.orZero(),
        )
    }
}
