package ekrem.ozan.satellites.data.mapper

import ekrem.ozan.satellites.base.extensions.orZero
import ekrem.ozan.satellites.data.Mapper
import ekrem.ozan.satellites.data.local.models.SatelliteDetailItem
import ekrem.ozan.satellites.domain.model.SatelliteDetailData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SatelliteDetailDataToDetailItemMapper @Inject constructor() : Mapper<SatelliteDetailData?, SatelliteDetailItem> {
    override fun toMapUiModel(model: SatelliteDetailData?): SatelliteDetailItem {
        return SatelliteDetailItem(
            model?.id.orZero(),
            model?.name.orEmpty(),
            model?.costPerLaunch.orZero(),
            model?.firstFlight.orEmpty(),
            model?.height.orZero(),
            model?.mass.orZero()
        )
    }
}
