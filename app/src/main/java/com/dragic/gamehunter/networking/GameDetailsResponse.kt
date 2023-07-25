package com.dragic.gamehunter.networking

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDetailsResponse(
    @SerialName("info")
    val info: GameInfoResponse,
    @SerialName("cheapestPriceEver")
    val cheapestPrice: GameCheapestPriceResponse,
    @SerialName("deals")
    val deals: List<DealDetailsResponse>,
)

@Serializable
data class GameInfoResponse(
    @SerialName("title")
    val title: String,
    @SerialName("thumb")
    val thumbnail: String,
)

@Serializable
data class GameCheapestPriceResponse(
    @SerialName("price")
    val price: String,
    @SerialName("date")
    val date: Int,
)

@Serializable
data class DealDetailsResponse(
    @SerialName("storeID")
    val storeId: String,
    @SerialName("dealID")
    val dealId: String,
    @SerialName("price")
    val salePrice: String,
    @SerialName("retailPrice")
    val normalPrice: String,
    @SerialName("savings")
    val savings: String,
)
