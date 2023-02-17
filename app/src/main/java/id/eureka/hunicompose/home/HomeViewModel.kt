package id.eureka.hunicompose.home

import androidx.lifecycle.ViewModel
import id.eureka.hunicompose.core.util.Utils
import id.eureka.hunicompose.home.model.Huni
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _huniItems: MutableStateFlow<List<Huni>> = MutableStateFlow(listOf())
    val huniItems = _huniItems.asStateFlow()

    init {
        _huniItems.value = Utils.dummyHuniItem()
    }
}