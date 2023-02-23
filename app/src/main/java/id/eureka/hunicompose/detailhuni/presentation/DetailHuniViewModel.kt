package id.eureka.hunicompose.detailhuni.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import id.eureka.hunicompose.home.presentation.model.Huni
import kotlinx.coroutines.flow.StateFlow

class DetailHuniViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val huni: StateFlow<Huni?> = savedStateHandle.getStateFlow("huni", null)

    fun setHuniDetail(huni: Huni) {
        savedStateHandle["huni"] = huni
    }

    //TODO replace filtering method in review view model into this
}