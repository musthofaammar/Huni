package id.eureka.hunicompose.detailhuni.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.eureka.hunicompose.detailhuni.data.model.Review
import id.eureka.hunicompose.home.presentation.model.Huni
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailHuniViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val huni: StateFlow<Huni?> = savedStateHandle.getStateFlow("huni", null)

    private val _groupedReviews: MutableStateFlow<Map<Int, List<Review>>> =
        MutableStateFlow(mapOf())

    private val _filteredReviews: MutableStateFlow<List<Review>> = MutableStateFlow(listOf())
    val filteredReviews = _filteredReviews.asStateFlow()

    private val _selectedRate: MutableStateFlow<Int> = MutableStateFlow(0)
    val selectedRate = _selectedRate.asStateFlow()

    private val _reviewCounts: MutableStateFlow<List<Int>> = MutableStateFlow(listOf())
    val reviewCounts = _reviewCounts.asStateFlow()

    fun setHuniDetail(huni: Huni) {
        viewModelScope.launch {
            savedStateHandle["huni"] = huni
            launch(Dispatchers.Default) {
                _groupedReviews.value = huni.reviews.groupBy { it.rate }

                val reviewCounts = mutableListOf<Int>()
                reviewCounts.add(huni.reviews.size)

                for (i in 5 downTo 1) {
                    reviewCounts.add(_groupedReviews.value[i]?.size ?: 0)
                }

                _reviewCounts.value = reviewCounts
            }

            filterReviews()
        }
    }

    //TODO replace filtering method in review view model into this

    fun setSelectedRate(position: Int) {
        viewModelScope.launch {
            _selectedRate.value = position
            filterReviews()
        }
    }

    //TODO filter review based on rate
    private fun filterReviews() {
        viewModelScope.launch {
            launch(Dispatchers.Default) {
                val position = _selectedRate.value
                _filteredReviews.value =
                    if (position != 0)
                        _groupedReviews.value[6 - position].orEmpty()
                    else _groupedReviews.value.values.toList().flatten().shuffled()
            }
        }
    }

}