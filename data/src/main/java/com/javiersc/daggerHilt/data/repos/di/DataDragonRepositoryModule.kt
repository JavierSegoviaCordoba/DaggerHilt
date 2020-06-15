package com.javiersc.daggerHilt.data.repos.di

import com.javiersc.daggerHilt.data.repos.DataDragonRepoImpl
import com.javiersc.daggerHilt.domain.repos.DataDragonRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
internal abstract class DataDragonRepositoryModule {

    @Binds
    abstract fun bindsDataDragonRepository(dataDragonRepo: DataDragonRepoImpl): DataDragonRepo
}
