package com.dragic.gamehunter.networking

data class DealResponse(
    val id: Int,
    val gameTitle: String,
    val salePrice: Double,
    val normalPrice: Double,
    val savePercentage: Double,
    val steamRating: Double,
    val dealRating: Double,
    val thumbnail: String,
)
