package com.dragic.gamehunter.viewmodel

import androidx.lifecycle.ViewModel
import com.dragic.gamehunter.repository.DealRepository
import com.dragic.gamehunter.utils.toFavoriteGameViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: DealRepository,
) : ViewModel() {
    val favoriteGames = repository.dealData().map { it.toFavoriteGameViewState() }
}
