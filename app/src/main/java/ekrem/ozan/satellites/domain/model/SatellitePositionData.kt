package ekrem.ozan.satellites.domain.model

data class SatellitePositionData(
    val list: ArrayList<PositionList>
)

data class PositionList(
    val id: Int,
    val positions: List<Position>
)

data class Position(
    val posX: Double,
    val posY: Double
)
