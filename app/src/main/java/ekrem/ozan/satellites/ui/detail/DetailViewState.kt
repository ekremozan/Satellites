package ekrem.ozan.satellites.ui.detail

import ekrem.ozan.satellites.domain.model.SatelliteDetailData

data class DetailViewState(val satellite: SatelliteDetailData) {
    fun getName() = satellite.name
    fun getDate() = satellite.firstFlight
    fun getHeightAndMass() = "${satellite.height}/${satellite.mass}"
    fun getCost() = satellite.costPerLaunch.toString()
    fun getPositions() = "${satellite.posX}/${satellite.posY}"
}