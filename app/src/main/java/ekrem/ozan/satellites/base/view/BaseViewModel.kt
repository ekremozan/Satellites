package ekrem.ozan.satellites.base.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn

abstract class BaseViewModel : ViewModel() {

    fun <T> Flow<T>.launch() {
        launchIn(viewModelScope)
    }
}

