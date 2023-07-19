package com.dragic.gamehunter.viewmodel

import com.dragic.gamehunter.model.GameDetailsDummyData
import com.dragic.gamehunter.utils.toDealDetailsViewState
import com.dragic.gamehunter.utils.toImageContentViewState

class GameDetailsViewModel(
    private val gameDetailsDummyData: GameDetailsDummyData
) {
    val dummyGameInfo = gameDetailsDummyData.data.toImageContentViewState()
    val dummyDealList = gameDetailsDummyData.data.deals.map { it.toDealDetailsViewState() }
}
