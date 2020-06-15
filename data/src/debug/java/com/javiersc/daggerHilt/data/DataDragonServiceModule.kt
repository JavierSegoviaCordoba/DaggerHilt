package com.javiersc.daggerHilt.data

import com.javiersc.daggerHilt.data.remote.DataDragonServiceFactory
import com.javiersc.daggerHilt.data.remote.services.DataDragonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
internal class DataDragonServiceModule {

    @Provides
    fun providesDataDragonService(): DataDragonService = DataDragonServiceFactory.pro
}
