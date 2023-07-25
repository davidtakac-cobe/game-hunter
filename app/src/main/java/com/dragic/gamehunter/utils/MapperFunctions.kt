package com.dragic.gamehunter.utils

import com.dragic.gamehunter.model.DealEntity
import com.dragic.gamehunter.model.GameCheapestPrice
import com.dragic.gamehunter.model.GameDetailsDeal
import com.dragic.gamehunter.model.GameDetailsEntity
import com.dragic.gamehunter.model.GameInfo
import com.dragic.gamehunter.networking.DealDetailsResponse
import com.dragic.gamehunter.networking.DealResponse
import com.dragic.gamehunter.networking.GameDetailsResponse
import com.dragic.gamehunter.view.favorites.FavoriteGameViewState
import com.dragic.gamehunter.view.gamedetails.DealDetailsViewState
import com.dragic.gamehunter.view.gamedetails.ImageContentViewState
import com.dragic.gamehunter.view.home.DealViewState
import kotlin.math.roundToInt

fun DealEntity.toDealViewState(): DealViewState =
    DealViewState(
        id = id.toInt(),
        gameTitle = gameTitle,
        salePrice = "$salePrice $",
        normalPrice = "$normalPrice $",
        savePercentage = "-$savings% OFF",
        steamRating = steamRating,
        dealRating = dealRating,
        thumbnail = thumbnail,
    )

fun GameDetailsEntity.toImageContentViewState(): ImageContentViewState =
    ImageContentViewState(
        gameTitle = info.title,
        thumbnail = info.thumbnail,
        lowestPrice = "${cheapestPrice.price} $",
        dateLowestPrice = getDateString(cheapestPrice.date),
        isFavorite = false,
    )

fun GameDetailsDeal.toDealDetailsViewState(): DealDetailsViewState =
    DealDetailsViewState(
        dealId = dealId,
        storeId = "Steam",
        savePercentage = "-$savings% OFF",
        salePrice = "$salePrice $",
        normalPrice = "$normalPrice $",
    )

fun DealEntity.toFavoriteGameViewState(): FavoriteGameViewState =
    FavoriteGameViewState(
        dealId = id.toInt(),
        gameTitle = gameTitle,
        thumbnail = thumbnail,
    )

fun DealResponse.toDealEntity() = DealEntity(
    id = id,
    gameTitle = gameTitle,
    salePrice = salePrice,
    normalPrice = normalPrice,
    savings = savings.toDouble().roundToInt(),
    steamRating = steamRating,
    dealRating = dealRating,
    thumbnail = thumbnail
)

fun GameDetailsResponse.toGameDetailsEntity() = GameDetailsEntity(
    info = GameInfo(info.title, info.thumbnail),
    cheapestPrice = GameCheapestPrice(cheapestPrice.price, cheapestPrice.date),
    deals = deals.map { it.toGameDetailsDeal() },
)

fun DealDetailsResponse.toGameDetailsDeal() = GameDetailsDeal(
    storeId = storeId,
    dealId = dealId,
    salePrice = salePrice,
    normalPrice = normalPrice,
    savings = savings.toDouble().roundToInt(),
)
