package com.dragic.gamehunter.networking

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreInfoResponse(
    @SerialName("storeID")
    val storeId: String,
    @SerialName("storeName")
    val storeName: String,
    @SerialName("images")
    val images: ImageDataResponse,
)

@Serializable
data class ImageDataResponse(
    @SerialName("logo")
    val logo: String,
)
