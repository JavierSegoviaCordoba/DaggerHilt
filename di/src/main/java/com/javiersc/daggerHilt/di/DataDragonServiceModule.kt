package com.javiersc.daggerHilt.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.javiersc.daggerHilt.data.remote.DataDragonService
import com.javiersc.resources.networkResponse.retrofit.NetworkResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataDragonServiceModule {
    @Provides
    @Singleton
    fun providesDataDragonService(): DataDragonService = dataDragonService
}

internal val dataDragonService: DataDragonService
    get() {
        val converter = Json(block = { ignoreUnknownKeys = true })
            .asConverterFactory("application/json".toMediaType())

        val retrofit = Retrofit.Builder().apply {
            baseUrl("https://ddragon.leagueoflegends.com/cdn/")
            addCallAdapterFactory(NetworkResponseCallAdapterFactory())
            addConverterFactory(converter)
        }.build()

        return retrofit.create()
    }
