package com.dragic.gamehunter.utils

import com.dragic.gamehunter.model.Deal
import com.dragic.gamehunter.model.DealEntity
import com.dragic.gamehunter.model.GameDetailsEntity
import com.dragic.gamehunter.view.favorites.FavoriteGameViewState
import com.dragic.gamehunter.view.gamedetails.DealDetailsViewState
import com.dragic.gamehunter.view.gamedetails.ImageContentViewState
import com.dragic.gamehunter.view.home.DealViewState
import java.util.Date

fun DealEntity.toDealViewState(): DealViewState =
    DealViewState(
        id = id,
        gameTitle = gameTitle,
        salePrice = "$salePrice $",
        normalPrice = "$normalPrice $",
        savePercentage = "-${savePercentage.toInt()}% OFF",
        steamRating = steamRating.toString(),
        dealRating = dealRating.toString(),
        thumbnail = thumbnail,
    )

fun GameDetailsEntity.toImageContentViewState(): ImageContentViewState =
    ImageContentViewState(
        gameTitle = info.title,
        thumbnail = info.thumbnail,
        lowestPrice = cheapestPrice.price,
        dateLowestPrice = Date(cheapestPrice.date).toString(),
        isFavorite = false,
    )

fun Deal.toDealDetailsViewState(): DealDetailsViewState =
    DealDetailsViewState(
        dealId = dealId,
        storeId = "Steam",
        savePercentage = savePercentage,
        salePrice = salePrice,
        normalPrice = normalPrice,
    )

fun DealEntity.toFavoriteGameViewState(): FavoriteGameViewState =
    FavoriteGameViewState(
        dealId = id,
        gameTitle = gameTitle,
        thumbnail = thumbnail,
    )
