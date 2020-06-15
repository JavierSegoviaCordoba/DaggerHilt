package com.javiersc.daggerHilt.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.javiersc.daggerHilt.data.remote.services.DataDragonService
import com.javiersc.daggerHilt.data.remote.services.MockDataDragonService
import com.javiersc.resources.networkResponse.retrofit.NetworkResponseCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import retrofit2.mock.create
import java.util.concurrent.TimeUnit

object DataDragonServiceFactory {

    val mock: DataDragonService
        get() {
            val retrofit = buildRetrofit("https://mockDataDragon".toHttpUrl())

            val networkBehavior: NetworkBehavior = NetworkBehavior.create().apply { setDelay(1, TimeUnit.SECONDS) }
            val mockRetrofit: MockRetrofit = MockRetrofit.Builder(retrofit).networkBehavior(networkBehavior).build()
            val delegate = mockRetrofit.create<DataDragonService>()

            return MockDataDragonService(delegate)
        }

    val pro: DataDragonService = buildRetrofit("https://ddragon.leagueoflegends.com/cdn/".toHttpUrl()).create()

    fun buildRetrofit(httpUrl: HttpUrl): Retrofit {
        val converter = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }.asConverterFactory("application/json".toMediaType())

        return Retrofit.Builder().apply {
            baseUrl(httpUrl)
            addCallAdapterFactory(NetworkResponseCallAdapterFactory())
            addConverterFactory(converter)
        }.build()
    }
}
