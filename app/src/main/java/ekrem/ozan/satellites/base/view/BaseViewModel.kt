package ekrem.ozan.satellites.base.view

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.transform

abstract class BaseViewModel : ViewModel() {
    open fun handleIntent(extras: Bundle) {}

    fun <T> Flow<T>.launch() {
        launchIn(viewModelScope)
    }
}

