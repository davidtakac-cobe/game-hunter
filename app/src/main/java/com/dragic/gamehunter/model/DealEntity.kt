package com.dragic.gamehunter.model

data class DealEntity(
    val id: Int,
    val gameTitle: String,
    val salePrice: Double,
    val normalPrice: Double,
    val savePercentage: Double,
    val steamRating: Double,
    val dealRating: Double,
    val thumbnail: String,
)
