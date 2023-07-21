package com.dragic.gamehunter.viewmodel

import androidx.lifecycle.ViewModel
import com.dragic.gamehunter.repository.DealRepository
import com.dragic.gamehunter.utils.toDealDetailsViewState
import com.dragic.gamehunter.utils.toImageContentViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    private val repository: DealRepository,
) : ViewModel() {
    val dummyGameInfo = repository.gameDetailsData().toImageContentViewState()
    val dummyDealList = repository.gameDetailsData().deals.map { it.toDealDetailsViewState() }
}
