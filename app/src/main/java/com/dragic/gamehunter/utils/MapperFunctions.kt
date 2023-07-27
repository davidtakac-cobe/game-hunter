package com.dragic.gamehunter.utils

import com.dragic.gamehunter.model.DealEntity
import com.dragic.gamehunter.model.GameCheapestPrice
import com.dragic.gamehunter.model.GameDetailsDeal
import com.dragic.gamehunter.model.GameDetailsEntity
import com.dragic.gamehunter.model.GameInfo
import com.dragic.gamehunter.model.StoreInfoEntity
import com.dragic.gamehunter.networking.DealDetailsResponse
import com.dragic.gamehunter.networking.DealResponse
import com.dragic.gamehunter.networking.GameDetailsResponse
import com.dragic.gamehunter.networking.StoreInfoResponse
import com.dragic.gamehunter.view.favorites.FavoriteGameViewState
import com.dragic.gamehunter.view.gamedetails.DealDetailsViewState
import com.dragic.gamehunter.view.gamedetails.ImageContentViewState
import com.dragic.gamehunter.view.home.DealViewState
import gamehunterdb.GameEntity
import kotlin.math.roundToInt

const val BASE_STORE_LOGO_URL = "https://www.cheapshark.com"
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
        id = id,
        gameTitle = info.title,
        thumbnail = info.thumbnail,
        lowestPrice = "${cheapestPrice.price} $",
        dateLowestPrice = getDateString(cheapestPrice.date),
        isFavorite = isFavorite,
    )

fun GameDetailsDeal.toDealDetailsViewState(): DealDetailsViewState =
    DealDetailsViewState(
        dealId = dealId,
        storeId = storeId,
        storeName = storeName,
        storeLogo = "$BASE_STORE_LOGO_URL$storeLogo",
        savePercentage = "-$savings% OFF",
        salePrice = "$salePrice $",
        normalPrice = "$normalPrice $",
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

fun GameDetailsResponse.toGameDetailsEntity(id: Int) = GameDetailsEntity(
    id = id,
    info = GameInfo(info.title, info.thumbnail),
    cheapestPrice = GameCheapestPrice(cheapestPrice.price, cheapestPrice.date),
    deals = deals.map { it.toGameDetailsDeal() },
    isFavorite = false,
)

fun DealDetailsResponse.toGameDetailsDeal() = GameDetailsDeal(
    storeId = storeId,
    storeName = "",
    storeLogo = "",
    dealId = dealId,
    salePrice = salePrice,
    normalPrice = normalPrice,
    savings = savings.toDouble().roundToInt(),
)

fun GameEntity.toFavoriteGameViewState() = FavoriteGameViewState(
    dealId = id.toInt(),
    gameTitle = title,
    thumbnail = thumbnail,
)

fun StoreInfoResponse.toStoreInfoEntity() = StoreInfoEntity(
    storeId = storeId,
    storeName = storeName,
    logo = images.logo,
)
