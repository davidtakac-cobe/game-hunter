package com.dragic.gamehunter.networking

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

interface CheapSharkApi {

    suspend fun getAllDeals(): List<DealResponse>

    suspend fun getGameDetails(gameId: Int): GameDetailsResponse

    suspend fun getStoreInfo(): List<StoreInfoResponse>
}

private const val BASE_URL = "https://www.cheapshark.com/api/1.0"

class CheapSharkApiImpl @Inject constructor(private val client: HttpClient) : CheapSharkApi {
    override suspend fun getAllDeals(): List<DealResponse> = client.get("$BASE_URL/deals").body()
    override suspend fun getGameDetails(gameId: Int): GameDetailsResponse = client.get("$BASE_URL/games?id=$gameId").body()

    override suspend fun getStoreInfo(): List<StoreInfoResponse> = client.get("$BASE_URL/stores").body()
}
