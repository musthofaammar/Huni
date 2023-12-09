package id.eureka.hunicompose.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.eureka.hunicompose.core.util.Utils
import id.eureka.hunicompose.home.presentation.model.HomeUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _nearbyUIState: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState.Init)
    val nearbyUIState = _nearbyUIState.asStateFlow()

    private val _popularUIState: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState.Init)
    val popularUIState = _popularUIState.asStateFlow()

    fun getHuniNearby() {
        viewModelScope.launch {
            launch(Dispatchers.IO) {
                setLoading(_nearbyUIState)
                delay(5000)
                _nearbyUIState.emit(
                    HomeUIState.HuniNearbyLoaded(
                        Utils.dummyHuniItem()
                    )
                )
            }
        }
    }

    private suspend fun setLoading(state: MutableStateFlow<HomeUIState>) {
        state.emit(HomeUIState.Loading)
    }

    fun getHuniPopular() {
        viewModelScope.launch {
            launch(Dispatchers.IO) {
                setLoading(_popularUIState)
                delay(5000)
                _popularUIState.emit(
                    HomeUIState.HuniPopularLoaded(
                        Utils.dummyHuniItem()
                    )
                )
            }
        }
    }
}