package ekrem.ozan.satellites.ui.detail

data class DetailUIState(val loading: Boolean) {
    fun showProgress(): Boolean = loading
}

