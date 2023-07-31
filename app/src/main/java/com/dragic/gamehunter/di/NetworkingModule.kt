package com.dragic.gamehunter.di

import com.dragic.gamehunter.networking.CheapSharkApi
import com.dragic.gamehunter.networking.CheapSharkApiImpl
import com.dragic.gamehunter.networking.httpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Singleton
    @Provides
    fun provideKtorClient() = httpClient

    @Singleton
    @Provides
    fun provideApiService(httpClient: HttpClient): CheapSharkApi = CheapSharkApiImpl(httpClient)
}
