package com.dragic.gamehunter.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
    val dealData: MutableState<List<DealViewState>> = mutableStateOf(emptyList())

    init {
        viewModelScope.launch(bgDispatcher) {
            dealData.value = repository.dealData().sortedByDescending { it.dealRating.toDouble() }.map { it.toDealViewState() }
        }
    }
}
