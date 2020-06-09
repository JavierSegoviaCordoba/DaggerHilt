package com.javiersc.daggerHilt.di

import com.javiersc.daggerHilt.data.DataDragonRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import domain.DataDragonRepo

@Module
@InstallIn(ApplicationComponent::class)
abstract class DataDragonRepositoryModule {

    @Binds
    abstract fun bindsDataDragonRepository(dataDragonRepo: DataDragonRepoImpl): DataDragonRepo
}
