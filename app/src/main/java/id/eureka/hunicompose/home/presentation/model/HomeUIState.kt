package id.eureka.hunicompose.home.presentation.model

sealed interface HomeUIState {

    object Init : HomeUIState
    object Loading : HomeUIState
    object Empty : HomeUIState
    data class HuniPopularLoaded(val data: List<Huni>) : HomeUIState
    data class HuniNearbyLoaded(val data: List<Huni>) : HomeUIState
}