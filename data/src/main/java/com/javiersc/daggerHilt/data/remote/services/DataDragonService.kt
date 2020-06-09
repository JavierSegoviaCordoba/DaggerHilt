package com.javiersc.daggerHilt.data.remote.services

import com.javiersc.daggerHilt.data.remote.models.ChampionsDTO
import com.javiersc.resources.networkResponse.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DataDragonService {
    @GET("{version}/data/{region}/champion.json")
    suspend fun getChampions(
        @Path("version") version: String,
        @Path("region") region: String
    ): NetworkResponse<ChampionsDTO, Unit>
}
    