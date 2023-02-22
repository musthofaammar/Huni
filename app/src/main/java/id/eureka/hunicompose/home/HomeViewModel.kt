package id.eureka.hunicompose.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.eureka.hunicompose.core.util.Utils
import id.eureka.hunicompose.home.model.HomeUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _homeUIState: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState.Init)
    val homeUIState = _homeUIState.asStateFlow()

    fun getHuniNearby() {
        viewModelScope.launch {
            setLoading()
            delay(5000)
            _homeUIState.emit(
                HomeUIState.HuniNearbyLoaded(
                    Utils.dummyHuniItem()
                )
            )
        }
    }

    private suspend fun setLoading() {
        _homeUIState.emit(HomeUIState.Loading)
    }

    fun getHuniPopular() {
        viewModelScope.launch {
            setLoading()
            delay(100)
            _homeUIState.emit(
                HomeUIState.HuniPopularLoaded(
                    Utils.dummyHuniItem()
                )
            )
        }
    }
}