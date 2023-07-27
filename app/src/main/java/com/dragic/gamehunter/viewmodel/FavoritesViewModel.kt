package com.dragic.gamehunter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dragic.gamehunter.di.MAIN_DISPATCHER
import com.dragic.gamehunter.repository.DealRepository
import com.dragic.gamehunter.utils.toFavoriteGameViewState
import com.dragic.gamehunter.view.favorites.FavoriteGameViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    repository: DealRepository,
    @Named(MAIN_DISPATCHER) mainDispatcher: CoroutineContext,
) : ViewModel() {

    var favoriteGames by mutableStateOf<List<FavoriteGameViewState>>(emptyList())
        private set

    init {
        viewModelScope.launch(mainDispatcher) {
            repository.getFavoriteGames().collect { games ->
                favoriteGames = games.map { it.toFavoriteGameViewState() }
            }
        }
    }
}
