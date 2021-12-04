package ekrem.ozan.satellites.ui.list

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import ekrem.ozan.satellites.R
import ekrem.ozan.satellites.domain.model.SatelliteData

data class SatelliteViewState(val satellite: SatelliteData) {

    fun getName() = satellite.name

    fun getStatusText() = when (satellite.active) {
        true -> "Active"
        false -> "Passive"
    }

    fun getStatusIcon(context: Context): Drawable? {
        return if (satellite.active) {
            ContextCompat.getDrawable(context, R.drawable.satellite_active_status_circle)
        } else {
            ContextCompat.getDrawable(context, R.drawable.satellite_passive_status_circle)
        }
    }
}