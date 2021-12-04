package ekrem.ozan.satellites.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import ekrem.ozan.satellites.R
import ekrem.ozan.satellites.base.view.BaseActivity

class HomeActivity : BaseActivity(R.layout.activity_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}