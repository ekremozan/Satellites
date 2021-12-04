package ekrem.ozan.satellites.ui.list

data class SatelliteUIState(val loading: Boolean) {
    fun showProgress(): Boolean = loading
}

