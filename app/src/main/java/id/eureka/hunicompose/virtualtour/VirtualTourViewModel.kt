package id.eureka.hunicompose.virtualtour

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VirtualTourViewModel : ViewModel() {

    private val _currentIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    val currentIndex = _currentIndex.asStateFlow()

    private val _rooms: MutableStateFlow<List<VirtualTourRoom>> = MutableStateFlow(listOf())
    val rooms = _rooms.asStateFlow()

    fun setIndex(index: Int) {
        viewModelScope.launch {
            _currentIndex.value = index
        }
    }

    fun setImages() {
        viewModelScope.launch {
            _rooms.value = listOf(
                VirtualTourRoom(
                    "https://images.unsplash.com/photo-1594904578869-c011783103c7?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1885&q=80",
                    "Parlor"
                ),
                VirtualTourRoom(
                    "https://images.unsplash.com/photo-1565516776528-b699a8fda9f2?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1857&q=80",
                    "Master Bedroom"
                ),
                VirtualTourRoom(
                    "https://images.unsplash.com/photo-1595406497243-c838ba7fa60f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1881&q=80",
                    "Guest Bedroom"
                ),
                VirtualTourRoom(
                    "https://images.unsplash.com/photo-1567372861533-4d063da25674?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80",
                    "Kitchen"
                ),
                VirtualTourRoom(
                    "https://images.unsplash.com/photo-1594446331804-c6159cf7cfd1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8MzYwJTIwZGVncmVlc3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=2000&q=60",
                    "Bathroom"
                ),
                VirtualTourRoom(
                    "https://images.unsplash.com/photo-1557971370-e7298ee473fb?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mjh8fDM2MCUyMGRlZ3JlZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=2000&q=60",
                    "Family Room"
                )
            )
        }
    }
}