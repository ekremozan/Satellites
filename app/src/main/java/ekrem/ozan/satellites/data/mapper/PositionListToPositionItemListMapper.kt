package ekrem.ozan.satellites.data.mapper

import ekrem.ozan.satellites.data.Mapper
import ekrem.ozan.satellites.data.local.models.SatellitePositionItem
import ekrem.ozan.satellites.domain.model.PositionList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PositionListToPositionItemListMapper @Inject constructor() : Mapper<PositionList?, List<SatellitePositionItem>> {
    override fun toMapUiModel(model: PositionList?): List<SatellitePositionItem> {
        return model?.positions?.map {
            SatellitePositionItem(
                model.id,
                it.posX,
                it.posY
            )
        } ?: listOf()
    }
}
