package ekrem.ozan.satellites.domain.model

data class SatelliteDetailFileData(
    val id: Int,
    val cost_per_launch: Int,
    val first_flight: String,
    val height: Int,
    val mass: Int
)