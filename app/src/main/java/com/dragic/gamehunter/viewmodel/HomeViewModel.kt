package com.dragic.gamehunter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dragic.gamehunter.di.BG_DISPATCHER
import com.dragic.gamehunter.repository.DealRepository
import com.dragic.gamehunter.utils.toDealViewState
import com.dragic.gamehunter.view.home.DealViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DealRepository,
    @Named(BG_DISPATCHER) private val bgDispatcher: CoroutineContext,
) : ViewModel() {
    var dealData by mutableStateOf<List<DealViewState>>(emptyList())
        private set

    var sortDialogState by mutableStateOf(false)

    init {
        viewModelScope.launch(bgDispatcher) {
            dealData = repository.dealData().map { it.toDealViewState() }
        }
    }

    fun setShowDialog(showDialog: Boolean) {
        sortDialogState = showDialog
    }

    fun fetchDealsByDealRating() {
        viewModelScope.launch(bgDispatcher) {
            dealData = repository.dealDataByDealRating().map { it.toDealViewState() }
        }
    }

    fun fetchDealsBySavings() {
        viewModelScope.launch(bgDispatcher) {
            dealData = repository.dealDataBySavings().map { it.toDealViewState() }
        }
    }

    fun fetchDealsByReviews() {
        viewModelScope.launch(bgDispatcher) {
            dealData = repository.dealDataByReviews().map { it.toDealViewState() }
        }
    }
}
