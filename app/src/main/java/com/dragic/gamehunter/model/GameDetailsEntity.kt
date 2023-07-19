package com.dragic.gamehunter.model

data class GameDetailsEntity(
    val info: GameInfo,
    val cheapestPrice: GameCheapestPrice,
    val deals: List<Deal>,
)

data class GameInfo(
    val title: String,
    val thumbnail: String,
)

data class GameCheapestPrice(
    val price: String,
    val date: Long,
)

data class Deal(
    val dealId: String,
    val storeId: String,
    val salePrice: String,
    val normalPrice: String,
    val savePercentage: String,
)
