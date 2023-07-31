package com.dragic.gamehunter.view.home

data class DealViewState(
    val id: Int,
    val gameTitle: String,
    val salePrice: String,
    val normalPrice: String,
    val savePercentage: String,
    val steamRating: String,
    val dealRating: String,
    val thumbnail: String,
)
