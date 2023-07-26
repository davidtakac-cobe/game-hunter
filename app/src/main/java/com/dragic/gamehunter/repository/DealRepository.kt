package com.dragic.gamehunter.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.dragic.gamehunter.GameHunterDatabase
import com.dragic.gamehunter.model.DealEntity
import com.dragic.gamehunter.model.GameDetailsEntity
import com.dragic.gamehunter.networking.CheapSharkApi
import com.dragic.gamehunter.utils.toDealEntity
import com.dragic.gamehunter.utils.toGameDetailsEntity
import gamehunterdb.GameEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject
import javax.inject.Singleton

interface DealRepository {

    suspend fun dealData(): List<DealEntity>

    suspend fun gameDetailsData(gameId: Int): Flow<GameDetailsEntity>

    suspend fun fetchGameDetailsData(gameId: Int)

    suspend fun removeGameById(gameId: Long)

    suspend fun insertGame(gameTitle: String, thumbnail: String, id: Long? = null)

    fun getFavoriteGames(): Flow<List<GameEntity>>

}

@Singleton
class DealRepositoryImpl @Inject constructor(
    private val cheapSharkApi: CheapSharkApi,
    database: GameHunterDatabase,
) : DealRepository {

    private val queries = database.gameEntityQueries
    private val gameDetails = MutableSharedFlow<GameDetailsEntity>()
    override suspend fun dealData(): List<DealEntity> = cheapSharkApi.getAllDeals().map { it.toDealEntity() }

    override suspend fun gameDetailsData(gameId: Int): Flow<GameDetailsEntity> =
        combine(
            gameDetails,
            getFavoriteGames(),
        ) { game, favoriteGames ->
            val favGame = favoriteGames.find { game.id == it.id.toInt() }
            game.copy(isFavorite = favGame != null)
        }

    override suspend fun fetchGameDetailsData(gameId: Int) = gameDetails.emit(cheapSharkApi.getGameDetails(gameId).toGameDetailsEntity(id = gameId))

    override fun getFavoriteGames(): Flow<List<GameEntity>> = queries.getAllGames().asFlow().mapToList(Dispatchers.IO)

    override suspend fun removeGameById(gameId: Long) = queries.deleteGameById(gameId)

    override suspend fun insertGame(gameTitle: String, thumbnail: String, id: Long?) = queries.insertGame(
        id = id,
        title = gameTitle,
        thumbnail = thumbnail
    )
}
