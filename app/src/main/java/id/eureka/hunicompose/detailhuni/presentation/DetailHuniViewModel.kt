package id.eureka.hunicompose.detailhuni.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.eureka.hunicompose.detailhuni.data.model.Review
import id.eureka.hunicompose.home.presentation.model.Huni
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailHuniViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val huni: StateFlow<Huni?> = savedStateHandle.getStateFlow("huni", null)

    private val _filteredReviews: MutableStateFlow<Map<Int, List<Review>>> =
        MutableStateFlow(mapOf())
    val filteredReviews = _filteredReviews.asStateFlow()

    private val _selectedRate: MutableStateFlow<Int> = MutableStateFlow(0)
    val selectedRate = _selectedRate.asStateFlow()

    fun setHuniDetail(huni: Huni) {
        savedStateHandle["huni"] = huni
        _filteredReviews.value = huni.reviews.groupBy { it.rate }
    }

    //TODO replace filtering method in review view model into this

    fun setSelectedRate(position: Int) {
        viewModelScope.launch {
            _selectedRate.value = position
        }
    }
}