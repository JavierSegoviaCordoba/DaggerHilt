package com.javiersc.daggerHilt.di

import com.javiersc.daggerHilt.data.DataDragonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import domain.DataDragonRepository
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class DataDragonRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsDataDragonRepository(dataDragonRepository: DataDragonRepositoryImpl): DataDragonRepository
}
