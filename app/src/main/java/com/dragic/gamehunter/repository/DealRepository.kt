package com.dragic.gamehunter.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.dragic.gamehunter.GameHunterDatabase
import com.dragic.gamehunter.model.DealEntity
import com.dragic.gamehunter.model.GameDetailsDeal
import com.dragic.gamehunter.model.GameDetailsEntity
import com.dragic.gamehunter.model.StoreInfoEntity
import com.dragic.gamehunter.networking.CheapSharkApi
import com.dragic.gamehunter.utils.toDealEntity
import com.dragic.gamehunter.utils.toGameDetailsEntity
import com.dragic.gamehunter.utils.toStoreInfoEntity
import gamehunterdb.GameEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject
import javax.inject.Singleton

interface DealRepository {

    suspend fun dealData(): List<DealEntity>

    fun gameDetailsData(gameId: Int): Flow<GameDetailsEntity>

    suspend fun fetchGameDetailsData(gameId: Int)

    suspend fun removeGameById(gameId: Long)

    suspend fun insertGame(gameTitle: String, thumbnail: String, id: Long? = null)

    suspend fun fetchStoreInfo()

    fun getFavoriteGames(): Flow<List<GameEntity>>

    fun getGameDealsDetails(): Flow<List<GameDetailsDeal>>

    suspend fun dealDataByDealRating(): List<DealEntity>

    suspend fun dealDataBySavings(): List<DealEntity>

    suspend fun dealDataByReviews(): List<DealEntity>

}

@Singleton
class DealRepositoryImpl @Inject constructor(
    private val cheapSharkApi: CheapSharkApi,
    database: GameHunterDatabase,
) : DealRepository {

    private val queries = database.gameEntityQueries
    private val gameDetails = MutableSharedFlow<GameDetailsEntity>()
    private val storeInfo = MutableSharedFlow<List<StoreInfoEntity>>()
    override suspend fun dealData(): List<DealEntity> = cheapSharkApi.getAllDeals().map { it.toDealEntity() }

    override fun gameDetailsData(gameId: Int): Flow<GameDetailsEntity> =
        combine(
            gameDetails,
            getFavoriteGames(),
        ) { game, favoriteGames ->
            val favGame = favoriteGames.find { game.id == it.id.toInt() }
            game.copy(isFavorite = favGame != null)
        }

    override suspend fun fetchGameDetailsData(gameId: Int) = gameDetails.emit(cheapSharkApi.getGameDetails(gameId).toGameDetailsEntity(id = gameId))

    override fun getFavoriteGames(): Flow<List<GameEntity>> = queries.getAllGames().asFlow().mapToList(Dispatchers.IO)
    override fun getGameDealsDetails(): Flow<List<GameDetailsDeal>> =
        combine(
            gameDetails,
            storeInfo
        ) { details, info ->
            val storeIdToStoreInfoMap = info.associateBy { it.storeId }
            details.deals.map {
                it.copy(
                    storeName = storeIdToStoreInfoMap[it.storeId]!!.storeName,
                    storeLogo = storeIdToStoreInfoMap[it.storeId]!!.logo,
                )
            }
        }

    override suspend fun dealDataByDealRating(): List<DealEntity> = cheapSharkApi.getAllDealsByDealRating().map { it.toDealEntity() }

    override suspend fun dealDataBySavings(): List<DealEntity> = cheapSharkApi.getAllDealsBySavings().map { it.toDealEntity() }

    override suspend fun dealDataByReviews(): List<DealEntity> = cheapSharkApi.getAllDealsByReviews().map { it.toDealEntity() }

    override suspend fun removeGameById(gameId: Long) = queries.deleteGameById(gameId)

    override suspend fun insertGame(gameTitle: String, thumbnail: String, id: Long?) = queries.insertGame(
        id = id,
        title = gameTitle,
        thumbnail = thumbnail
    )

    override suspend fun fetchStoreInfo() = storeInfo.emit(cheapSharkApi.getStoreInfo().map { it.toStoreInfoEntity() })
}
