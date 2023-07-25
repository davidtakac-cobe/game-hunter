package com.dragic.gamehunter.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dragic.gamehunter.repository.DealRepository
import com.dragic.gamehunter.utils.toFavoriteGameViewState
import com.dragic.gamehunter.view.favorites.FavoriteGameViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: DealRepository,
) : ViewModel() {
    val dealData: MutableState<List<FavoriteGameViewState>> = mutableStateOf(emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            dealData.value = repository.dealData().sortedByDescending { it.dealRating }.map { it.toFavoriteGameViewState() }
        }
    }
}
