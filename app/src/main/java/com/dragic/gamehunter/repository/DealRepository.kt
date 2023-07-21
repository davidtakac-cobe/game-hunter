package com.dragic.gamehunter.repository

import com.dragic.gamehunter.model.DealDummyData
import com.dragic.gamehunter.model.DealEntity
import com.dragic.gamehunter.model.GameDetailsDummyData
import com.dragic.gamehunter.model.GameDetailsEntity
import javax.inject.Inject
import javax.inject.Singleton

interface DealRepository {

    fun dealData(): List<DealEntity>

    fun gameDetailsData(): GameDetailsEntity

}

@Singleton
class DealRepositoryImpl @Inject constructor(
    private val dealDummyData: DealDummyData,
    private val gameDetailsDummyData: GameDetailsDummyData,
) : DealRepository {
    override fun dealData(): List<DealEntity> = dealDummyData.deals

    override fun gameDetailsData(): GameDetailsEntity = gameDetailsDummyData.data
}
