package com.dragic.gamehunter.utils

import com.dragic.gamehunter.model.DealEntity
import com.dragic.gamehunter.view.home.DealViewState

fun DealEntity.toDealViewState(dealEntity: DealEntity): DealViewState =
    DealViewState(
        id = id,
        gameTitle = gameTitle,
        salePrice = "$salePrice $",
        normalPrice = "$normalPrice $",
        savePercentage = "-$savePercentage% OFF",
        steamRating = steamRating.toString(),
        dealRating = dealRating.toString(),
        thumbnail = thumbnail,
    )
