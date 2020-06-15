package com.javiersc.daggerHilt.data.local.di

import android.content.Context
import com.javiersc.daggerHilt.data.local.AppDatabase
import com.javiersc.daggerHilt.data.local.daos.ChampionsDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
internal class AppDatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase = AppDatabase(context)

    @Provides
    @Singleton
    fun providesChampionsDao(appDatabase: AppDatabase): ChampionsDAO = appDatabase.championsDao()
}
