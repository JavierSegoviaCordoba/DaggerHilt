package com.javiersc.daggerHilt.di

import android.app.Application
import com.javiersc.daggerHilt.data.local.AppDatabase
import com.javiersc.daggerHilt.data.local.daos.ChampionsDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class AppDatabaseModule {

    @Provides
    fun providesDatabase(application: Application): AppDatabase = AppDatabase(application)

    @Provides
    fun providesChampionsDao(appDatabase: AppDatabase): ChampionsDAO = appDatabase.championsDao()
}
