package com.dragic.gamehunter.networking

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DealResponse(
    @SerialName("gameID")
    val id: String,
    @SerialName("title")
    val gameTitle: String,
    @SerialName("salePrice")
    val salePrice: String,
    @SerialName("normalPrice")
    val normalPrice: String,
    @SerialName("savings")
    val savings: String,
    @SerialName("steamRatingPercent")
    val steamRating: String,
    @SerialName("dealRating")
    val dealRating: String,
    @SerialName("thumb")
    val thumbnail: String,
)
