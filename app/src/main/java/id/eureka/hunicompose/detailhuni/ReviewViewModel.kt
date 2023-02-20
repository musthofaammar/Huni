package id.eureka.hunicompose.detailhuni

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.eureka.hunicompose.detailhuni.model.Review
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random

class ReviewViewModel : ViewModel() {

    //TODO save list review
    private val _reviews: MutableStateFlow<List<Review>> = MutableStateFlow(listOf())

    private val _filteredReviews: MutableStateFlow<List<Review>> = MutableStateFlow(listOf())
    val filteredReviews = _filteredReviews.asStateFlow()

    //TODO save selected rate

    private val _selectedRate: MutableStateFlow<Int> = MutableStateFlow(0)
    val selectedRate = _selectedRate.asStateFlow()

    init {
        initReviews()
    }

    //TODO init all reviews
    private fun initReviews() {
        viewModelScope.launch {
            val reviews = mutableListOf<Review>()
            var review: Review
            for (i in 0..Random.nextInt(10, 30)) {
                review = Review(
                    name = UUID.randomUUID().toString(),
                    rate = Random.nextInt(1, 5),
                    description = UUID.randomUUID().toString(),
                )

                reviews.add(review)
            }

            _reviews.value = reviews
            _filteredReviews.value = reviews
        }
    }

    //TODO set selected rate
    fun setSelectedRate(position: Int) {
        viewModelScope.launch {
            _selectedRate.value = position
            filterReviews()
        }
    }

    //TODO filter review based on rate
    private fun filterReviews() {
        val position = _selectedRate.value
        _filteredReviews.value =
            if (position != 0) _reviews.value.filter { it.rate == (5 - _selectedRate.value) } else _reviews.value
    }
}