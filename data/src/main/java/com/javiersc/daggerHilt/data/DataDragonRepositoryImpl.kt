package com.javiersc.daggerHilt.data

import com.javiersc.daggerHilt.data.remote.DataDragonService
import com.javiersc.daggerHilt.data.remote.models.ChampionsDTO
import com.javiersc.daggerHilt.data.remote.models.errorToDomain
import com.javiersc.daggerHilt.data.remote.models.internetNotAvailableToDomain
import com.javiersc.daggerHilt.data.remote.models.toDomain
import com.javiersc.daggerHilt.data.remote.models.unknownErrorToDomain
import com.javiersc.resources.networkResponse.extensions.toResource
import com.javiersc.resources.resource.Resource
import domain.DataDragonRepository
import domain.models.Champion
import domain.models.Error
import domain.models.Region
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DataDragonRepositoryImpl @Inject constructor(
    private val dataDragonService: DataDragonService,
) : DataDragonRepository {
    override fun getChampions(): Flow<Resource<List<Champion>, Error>> = flow {
        emit(Resource.Loading)
        emit(
            dataDragonService.getChampions("10.11.1", Region.EN_US.value).toResource(
                success = ChampionsDTO::toDomain,
                error = ::errorToDomain,
                unknownError = ::unknownErrorToDomain,
                internetNotAvailable = ::internetNotAvailableToDomain,
            )
        )
    }
}
