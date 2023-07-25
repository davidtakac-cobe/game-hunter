package com.dragic.gamehunter.repository

import com.dragic.gamehunter.model.DealEntity
import com.dragic.gamehunter.model.GameDetailsEntity
import com.dragic.gamehunter.networking.CheapSharkApi
import com.dragic.gamehunter.utils.toDealEntity
import com.dragic.gamehunter.utils.toGameDetailsEntity
import javax.inject.Inject
import javax.inject.Singleton

interface DealRepository {

    suspend fun dealData(): List<DealEntity>

    suspend fun gameDetailsData(gameId: Int): GameDetailsEntity

}

@Singleton
class DealRepositoryImpl @Inject constructor(
    private val cheapSharkApi: CheapSharkApi,
) : DealRepository {
    override suspend fun dealData(): List<DealEntity> = cheapSharkApi.getAllDeals().map { it.toDealEntity() }

    override suspend fun gameDetailsData(gameId: Int): GameDetailsEntity = cheapSharkApi.getGameDetails(gameId).toGameDetailsEntity()
}
