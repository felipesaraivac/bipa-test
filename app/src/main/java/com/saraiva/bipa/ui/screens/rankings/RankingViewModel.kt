package com.saraiva.bipa.ui.screens.rankings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saraiva.bipa.core.utils.Sorting
import com.saraiva.bipa.core.utils.State
import com.saraiva.bipa.domain.entity.NodeEntity
import com.saraiva.bipa.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

typealias NodeList = State<List<NodeEntity>>

@HiltViewModel
class RankingViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _nodes: MutableStateFlow<NodeList> =
        MutableStateFlow(State.loading())


    init {
        getTopRankings()
    }

    /**
     * Nodes fetching state management
     */

    fun getTopRankings() = viewModelScope.launch(Dispatchers.IO) {
        try {
            _nodes.value = State.success(repository.getNodes())
        } catch (exception: Exception) {
            exception.printStackTrace()
            _nodes.value = State.failed("${exception.message}")
        }
        _sorted.value = _nodes.value
    }

    /**
     * Refreshing state management
     */

    private val _refreshing: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val refreshing: StateFlow<Boolean> = _refreshing

    fun refresh() = viewModelScope.launch(Dispatchers.IO) {
        _refreshing.value = true
        getTopRankings().join()
        _refreshing.value = false
    }


    /**
     * Sorting state management
     */
    private val _sorting: MutableStateFlow<Sorting> = MutableStateFlow(Sorting.UNSORTED)

    private val _sorted: MutableStateFlow<NodeList> = MutableStateFlow(State.loading())

    fun sort() = viewModelScope.launch(Dispatchers.Unconfined) {
        val nodes = _nodes.value as State.Success
        _sorting.value = when (_sorting.value) {
            Sorting.UNSORTED -> {
                _sorted.value = State.Success(nodes.data.sortedBy { it.capacity })
                Sorting.BY_CAPACITY
            }

            Sorting.BY_CAPACITY -> {
                _sorted.value = State.Success(nodes.data.sortedBy { it.alias })
                Sorting.BY_NAME
            }

            else -> {
                _sorted.value = State.Success(nodes.data)
                Sorting.UNSORTED
            }
        }

    }

    val nodes: StateFlow<NodeList> = _sorted
}
