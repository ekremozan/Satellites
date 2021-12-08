package ekrem.ozan.satellites.ui.detail

import ekrem.ozan.satellites.domain.model.SatelliteDetailData
import java.text.DecimalFormat

data class DetailViewState(val satellite: SatelliteDetailData) {
    fun getName() = satellite.name
    fun getDate() = satellite.firstFlight
    fun getHeightAndMass() = "${satellite.height}/${satellite.mass}"
    fun getCost() = DecimalFormat("###,###,###").format(satellite.costPerLaunch).replace(",",".")
    fun getPositions() = "${satellite.posX}/${satellite.posY}"
}