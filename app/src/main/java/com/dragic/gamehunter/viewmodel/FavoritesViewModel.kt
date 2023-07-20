package com.dragic.gamehunter.viewmodel

import com.dragic.gamehunter.model.DealDummyData
import com.dragic.gamehunter.utils.toFavoriteGameViewState

class FavoritesViewModel(
    private val dealDummyData: DealDummyData
) {
    val favoriteGames = dealDummyData.deals.map { it.toFavoriteGameViewState() }
}
