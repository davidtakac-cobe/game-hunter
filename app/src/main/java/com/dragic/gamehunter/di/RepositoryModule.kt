package com.dragic.gamehunter.di

import com.dragic.gamehunter.model.DealDummyData
import com.dragic.gamehunter.model.GameDetailsDummyData
import com.dragic.gamehunter.repository.DealRepository
import com.dragic.gamehunter.repository.DealRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(dealDummyData: DealDummyData, gameDetailsDummyData: GameDetailsDummyData): DealRepository =
        DealRepositoryImpl(dealDummyData, gameDetailsDummyData)

}
