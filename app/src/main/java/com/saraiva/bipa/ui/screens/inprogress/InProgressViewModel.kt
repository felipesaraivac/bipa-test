package com.saraiva.bipa.ui.screens.inprogress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saraiva.bipa.core.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InProgresViewModel: ViewModel() {

    private val _state: MutableStateFlow<State<Boolean>> =
        MutableStateFlow(State.loading())
    val state: StateFlow<State<Boolean>> = _state

    init {
        waitForSometime()
    }

    fun waitForSometime() = viewModelScope.launch(Dispatchers.Unconfined) {
        try {
            Thread.sleep(3000)
            _state.value = State.success(true)
        } catch (exception: Exception) {
            exception.printStackTrace()
            _state.value = State.failed("${exception.message}")
        }
    }
}
