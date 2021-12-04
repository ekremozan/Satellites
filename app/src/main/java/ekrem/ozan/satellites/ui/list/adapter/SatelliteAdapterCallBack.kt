package ekrem.ozan.satellites.ui.list.adapter

import ekrem.ozan.satellites.domain.model.SatelliteData

interface SatelliteAdapterCallBack {
    fun onItemClick(satellite: SatelliteData)
}