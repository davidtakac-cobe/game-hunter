package com.dragic.gamehunter.viewmodel

import com.dragic.gamehunter.model.DealDummyData
import com.dragic.gamehunter.utils.toDealViewState

class HomeViewModel(
    private val dummyData: DealDummyData,
) {
    val dealData = dummyData.deals.sortedByDescending { it.dealRating }.map { it.toDealViewState(it) }
}
