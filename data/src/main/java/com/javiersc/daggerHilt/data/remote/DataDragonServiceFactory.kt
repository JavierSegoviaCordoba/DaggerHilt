package com.javiersc.daggerHilt.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.javiersc.daggerHilt.data.remote.services.DataDragonService
import com.javiersc.resources.networkResponse.retrofit.NetworkResponseCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

object DataDragonServiceFactory {

    fun pro(): DataDragonService {
        val converter = Json(block = { ignoreUnknownKeys = true })
            .asConverterFactory("application/json".toMediaType())

        val retrofit = Retrofit.Builder().apply {
            baseUrl("https://ddragon.leagueoflegends.com/cdn/")
            addCallAdapterFactory(NetworkResponseCallAdapterFactory())
            addConverterFactory(converter)
        }.build()

        return retrofit.create()
    }
}
