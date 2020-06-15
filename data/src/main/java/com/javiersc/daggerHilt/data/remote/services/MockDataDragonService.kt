package com.javiersc.daggerHilt.data.remote.services

import com.javiersc.daggerHilt.data.remote.extensions.readResource
import com.javiersc.daggerHilt.data.remote.models.ChampionsDTO
import com.javiersc.resources.networkResponse.NetworkResponse
import kotlinx.serialization.json.Json
import retrofit2.mock.BehaviorDelegate
import javax.inject.Inject

class MockDataDragonService @Inject constructor(
    private val delegate: BehaviorDelegate<DataDragonService>
) : DataDragonService {

    override suspend fun getChampions(version: String, region: String): NetworkResponse<ChampionsDTO, Unit> {
        return delegate.returningResponse(Json.parse(ChampionsDTO.serializer(), readResource("champions_200.json")))
            .getChampions(version, region)
    }
}
