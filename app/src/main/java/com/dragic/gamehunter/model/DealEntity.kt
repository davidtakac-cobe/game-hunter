package com.dragic.gamehunter.model

data class DealEntity(
    val id: String,
    val gameTitle: String,
    val salePrice: String,
    val normalPrice: String,
    val savings: Int,
    val steamRating: String,
    val dealRating: String,
    val thumbnail: String,
)
