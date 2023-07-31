package com.dragic.gamehunter.networking

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

interface CheapSharkApi {

    suspend fun getAllDeals(): List<DealResponse>

    suspend fun getGameDetails(gameId: Int): GameDetailsResponse

    suspend fun getStoreInfo(): List<StoreInfoResponse>

    suspend fun getAllDealsByDealRating(): List<DealResponse>

    suspend fun getAllDealsBySavings(): List<DealResponse>

    suspend fun getAllDealsByReviews(): List<DealResponse>
}

private const val BASE_URL = "https://www.cheapshark.com/api/1.0"
private const val DEAL_RATING = "Deal Rating"
private const val SAVINGS = "Savings"
private const val REVIEWS = "Reviews"

class CheapSharkApiImpl @Inject constructor(private val client: HttpClient) : CheapSharkApi {

    override suspend fun getAllDeals(): List<DealResponse> = client.get("$BASE_URL/deals").body()

    override suspend fun getGameDetails(gameId: Int): GameDetailsResponse = client.get("$BASE_URL/games?id=$gameId").body()

    override suspend fun getStoreInfo(): List<StoreInfoResponse> = client.get("$BASE_URL/stores").body()

    override suspend fun getAllDealsByDealRating(): List<DealResponse> = client.get("$BASE_URL/deals?sortBy=$DEAL_RATING").body()

    override suspend fun getAllDealsBySavings(): List<DealResponse> = client.get("$BASE_URL/deals?sortBy=$SAVINGS").body()

    override suspend fun getAllDealsByReviews(): List<DealResponse> = client.get("$BASE_URL/deals?sortBy=$REVIEWS").body()
}
