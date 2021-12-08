package ekrem.ozan.satellites.ui.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ekrem.ozan.satellites.base.view.BaseViewModel
import ekrem.ozan.satellites.domain.model.Position
import ekrem.ozan.satellites.domain.model.PositionList
import ekrem.ozan.satellites.domain.model.SatelliteDetailData
import ekrem.ozan.satellites.domain.usecase.*
import ekrem.ozan.satellites.util.Constants
import ekrem.ozan.satellites.util.Constants.Companion.EMPTY_STRING
import ekrem.ozan.satellites.util.Constants.Companion.MINUTE_COEFFICIENT
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private var getSatelliteDetailUseCase: GetSatelliteDetailUseCase,
    private var getSatellitePositionUseCase: GetSatellitePositionUseCase,
    private var checkLocalSatelliteUseCase: CheckLocalSatelliteUseCase,
    private var addSatelliteExistUseCase: InsertSatelliteUseCase,
    private var insertSatellitePositionsUseCase: InsertSatellitePositionsUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    private val _satelliteDetailLiveData by lazy { MutableLiveData<DetailViewState>() }
    val satelliteDetailLiveData: LiveData<DetailViewState> by lazy { _satelliteDetailLiveData }

    private val _uiStateLiveData by lazy { MutableLiveData<DetailUIState>() }
    val uiStateLiveData: LiveData<DetailUIState> by lazy { _uiStateLiveData }

    var satelliteId: Int = 0
    private var satelliteName: String = EMPTY_STRING

    init {
        savedStateHandle.get<Int>(Constants.SATELLITE_ID)?.let { id -> this.satelliteId = id }
        savedStateHandle.get<String>(Constants.SATELLITE_NAME)?.let { name -> this.satelliteName = name }
    }

    fun getData(context: Context, id: Int) {
        checkLocalSatelliteUseCase.invoke<Boolean>(satelliteId)
            .onStart { _uiStateLiveData.value = DetailUIState(true) }
            .onEach { isLocal ->
                when (isLocal) {
                    true -> getSatelliteFromDB(id)
                    false -> getSatelliteFromFile(context, id)
                }
            }.launch()
    }

    private fun getSatelliteFromDB(id: Int) {
        val satelliteReq = getSatelliteDetailUseCase.invoke<SatelliteDetailData>(GetSatelliteDetailUseCase.Params(id, true))
        val satellitePositionsReq = getSatellitePositionUseCase.invoke<SatelliteDetailData>(GetSatellitePositionUseCase.Params(id, true))

        satelliteReq.combine(satellitePositionsReq) { satellite, satellitePositions ->
            Pair(satellite, satellitePositions)
        }.onEach { item ->
            changePositionInfo(item.first, item.second.positions)
        }.launch()
    }

    private fun getSatelliteFromFile(context: Context, id: Int) {
        val satelliteReq = getSatelliteDetailUseCase.invoke<SatelliteDetailData>(GetSatelliteDetailUseCase.Params(satelliteId, false, context))
        val satellitePositionsReq = getSatellitePositionUseCase.invoke<PositionList>(GetSatellitePositionUseCase.Params(satelliteId, false, context))

        satelliteReq.combine(satellitePositionsReq) { satellite, satellitePositions ->
            satellite.apply {
                this.id = satelliteId
                name = satelliteName
            }
            Pair(satellite, satellitePositions)
        }.onEach { item ->
            insertSatellite(item.first)
            insertSatellitePositions(item.second)
            changePositionInfo(item.first, item.second.positions)
        }.launch()
    }

    private fun changePositionInfo(satelliteDetailData: SatelliteDetailData, positions: List<Position>) {
        viewModelScope.launch {
            generateSequence(positions) { it }.forEach {
                _uiStateLiveData.value = DetailUIState(true)
                it.map { item ->

                    satelliteDetailData.apply {
                        posX = item.posX.toString()
                        posY = item.posY.toString()
                    }

                    _satelliteDetailLiveData.value = DetailViewState(satelliteDetailData)
                    _uiStateLiveData.value = DetailUIState(false)
                    delay(3L * MINUTE_COEFFICIENT)
                }
            }
        }
    }

    private fun insertSatellite(satelliteDetailData: SatelliteDetailData) {
        addSatelliteExistUseCase.invoke<Unit>(satelliteDetailData).launch()
    }

    private fun insertSatellitePositions(satellite: PositionList) {
        insertSatellitePositionsUseCase.invoke<Unit>(satellite).launch()
    }
}