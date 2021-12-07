package ekrem.ozan.satellites.domain.model

import ekrem.ozan.satellites.util.Constants.Companion.EMPTY_STRING

data class SatelliteDetailData(
    var id: Int,
    var name: String = EMPTY_STRING,
    var costPerLaunch: Int,
    var firstFlight: String,
    var height: Int,
    var mass: Int,
    var posX: String = EMPTY_STRING,
    var posY: String = EMPTY_STRING
)