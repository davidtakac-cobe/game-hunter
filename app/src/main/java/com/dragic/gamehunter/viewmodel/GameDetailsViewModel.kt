package com.dragic.gamehunter.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dragic.gamehunter.di.GameId
import com.dragic.gamehunter.repository.DealRepository
import com.dragic.gamehunter.utils.toDealDetailsViewState
import com.dragic.gamehunter.utils.toImageContentViewState
import com.dragic.gamehunter.view.gamedetails.DealDetailsViewState
import com.dragic.gamehunter.view.gamedetails.ImageContentViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    private val repository: DealRepository,
    @GameId gameId: Int
) : ViewModel() {
    val gameData: MutableState<ImageContentViewState> = mutableStateOf(ImageContentViewState("", "", "", "", false))
    val dealData: MutableState<List<DealDetailsViewState>> = mutableStateOf(emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            gameData.value = repository.gameDetailsData(gameId).toImageContentViewState()
        }
        viewModelScope.launch(Dispatchers.IO) {
            dealData.value = repository.gameDetailsData(gameId).deals.map { it.toDealDetailsViewState() }
        }
    }
}
