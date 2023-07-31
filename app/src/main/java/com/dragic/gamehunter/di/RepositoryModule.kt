package com.dragic.gamehunter.di

import app.cash.sqldelight.db.SqlDriver
import com.dragic.gamehunter.GameHunterDatabase
import com.dragic.gamehunter.networking.CheapSharkApi
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
    fun provideRepository(cheapSharkApi: CheapSharkApi, driver: SqlDriver): DealRepository =
        DealRepositoryImpl(cheapSharkApi, GameHunterDatabase(driver))
}
