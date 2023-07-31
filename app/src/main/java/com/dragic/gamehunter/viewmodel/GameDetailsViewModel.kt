package com.dragic.gamehunter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dragic.gamehunter.di.BG_DISPATCHER
import com.dragic.gamehunter.di.GameId
import com.dragic.gamehunter.repository.DealRepository
import com.dragic.gamehunter.utils.toDealDetailsViewState
import com.dragic.gamehunter.utils.toImageContentViewState
import com.dragic.gamehunter.view.gamedetails.DealDetailsViewState
import com.dragic.gamehunter.view.gamedetails.ImageContentViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

const val DEAL_REDIRECT_LINK_PREFIX = "https://www.cheapshark.com/redirect?dealID="

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    private val repository: DealRepository,
    @GameId private val gameId: Int,
    @Named(BG_DISPATCHER) private val bgDispatcher: CoroutineContext,
) : ViewModel() {
    var gameData by mutableStateOf<ImageContentViewState?>(null)
        private set
    var dealData by mutableStateOf<List<DealDetailsViewState>>(emptyList())
        private set

    init {
        viewModelScope.launch(bgDispatcher) {
            repository.fetchGameDetailsData(gameId)
        }
        viewModelScope.launch(bgDispatcher) {
            repository.fetchStoreInfo()
        }
        viewModelScope.launch(bgDispatcher) {
            repository.gameDetailsData(gameId).collect { gameDetails ->
                gameData = gameDetails.toImageContentViewState()
            }
        }
        viewModelScope.launch(bgDispatcher) {
            repository.getGameDealsDetails().collect { dealDetails ->
                dealData = dealDetails.map { it.toDealDetailsViewState() }
            }
        }
    }

    fun refreshFavoriteMovie() {
        viewModelScope.launch(bgDispatcher) {
            val game = gameData
            if (game != null) {
                if (game.isFavorite) repository.removeGameById(gameId.toLong()) else repository.insertGame(
                    game.gameTitle,
                    game.thumbnail,
                    gameId.toLong(),
                )
            }
        }
    }

    fun dealUriFromDealId(dealId: String): String = "$DEAL_REDIRECT_LINK_PREFIX$dealId"
}
