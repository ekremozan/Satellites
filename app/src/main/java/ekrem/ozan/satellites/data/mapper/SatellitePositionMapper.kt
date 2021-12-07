package ekrem.ozan.satellites.data.mapper

import ekrem.ozan.satellites.data.Mapper
import ekrem.ozan.satellites.data.local.models.SatellitePositionItem
import ekrem.ozan.satellites.domain.model.Position
import ekrem.ozan.satellites.domain.model.PositionList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SatellitePositionMapper @Inject constructor() : Mapper<List<SatellitePositionItem>?, PositionList> {
    override fun toMapUiModel(model: List<SatellitePositionItem>?): PositionList {
        return PositionList(
            id = model?.firstOrNull()?.id ?: 0,
            positions = model?.map { Position(it.posX, it.posY) }?: listOf()
        )
    }
}
