package ekrem.ozan.satellites.ui.list

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ekrem.ozan.satellites.base.view.BaseViewModel
import ekrem.ozan.satellites.domain.model.SatelliteData
import ekrem.ozan.satellites.domain.usecase.SatelliteUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val satelliteUseCase: SatelliteUseCase
) : BaseViewModel() {

    private val _satelliteListLiveData by lazy { MutableLiveData<ArrayList<SatelliteData>>() }
    val satelliteListLiveData: LiveData<ArrayList<SatelliteData>> by lazy { _satelliteListLiveData }

    private val _uiStateLiveData by lazy { MutableLiveData<SatelliteUIState>() }
    val uiStateLiveData: LiveData<SatelliteUIState> by lazy { _uiStateLiveData }

    val satelliteList by lazy { ArrayList<SatelliteData>() }

    fun getSatellites(context: Context) {
        satelliteUseCase.invoke<SatelliteData>(context)
            .onStart {
                _uiStateLiveData.value = SatelliteUIState(true)
            }.onEach { list ->
                _satelliteListLiveData.value = list
                satelliteList.addAll(list)
            }.onCompletion {
                _uiStateLiveData.value = SatelliteUIState(false)
            }.launch()
    }
}