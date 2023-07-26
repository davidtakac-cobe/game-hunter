package com.dragic.gamehunter.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

const val BG_DISPATCHER = "BackgroundDispatcher"

@Module
@InstallIn(ViewModelComponent::class)
object ConcurrencyModule {

    @Provides
    @Named(BG_DISPATCHER)
    fun provideBackgroundDispatcher(): CoroutineContext = Dispatchers.IO
}
