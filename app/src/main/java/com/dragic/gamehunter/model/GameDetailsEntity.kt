package com.dragic.gamehunter.model

data class GameDetailsEntity(
    val id: Int,
    val info: GameInfo,
    val cheapestPrice: GameCheapestPrice,
    val deals: List<GameDetailsDeal>,
    val isFavorite: Boolean,
)

data class GameInfo(
    val title: String,
    val thumbnail: String,
)

data class GameCheapestPrice(
    val price: String,
    val date: Int,
)

data class GameDetailsDeal(
    val storeId: String,
    val dealId: String,
    val salePrice: String,
    val normalPrice: String,
    val savings: Int,
)
