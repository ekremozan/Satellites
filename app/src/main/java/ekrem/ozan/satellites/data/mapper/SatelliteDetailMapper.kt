package ekrem.ozan.satellites.data.mapper

import ekrem.ozan.satellites.base.extensions.orZero
import ekrem.ozan.satellites.data.Mapper
import ekrem.ozan.satellites.data.local.models.SatelliteDetailItem
import ekrem.ozan.satellites.domain.model.SatelliteDetailData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SatelliteDetailMapper @Inject constructor() : Mapper<SatelliteDetailItem?, SatelliteDetailData> {
    override fun toMapUiModel(model: SatelliteDetailItem?): SatelliteDetailData {
        return SatelliteDetailData(
            id = model?.id ?: 0,
            name = model?.name.orEmpty(),
            costPerLaunch = model?.costPerLaunch.orZero(),
            firstFlight = model?.firstFlight.orEmpty(),
            height = model?.height.orZero(),
            mass = model?.mass.orZero()
        )
    }
}
