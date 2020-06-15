package com.javiersc.daggerHilt.data.repos

import com.javiersc.daggerHilt.data.local.daos.ChampionsDAO
import com.javiersc.daggerHilt.data.local.models.ChampionEntity
import com.javiersc.daggerHilt.data.local.models.toDomain
import com.javiersc.daggerHilt.data.local.models.toEntity
import com.javiersc.daggerHilt.data.remote.models.ChampionsDTO
import com.javiersc.daggerHilt.data.remote.models.errorToDomain
import com.javiersc.daggerHilt.data.remote.models.internetNotAvailableToDomain
import com.javiersc.daggerHilt.data.remote.models.toDomain
import com.javiersc.daggerHilt.data.remote.models.unknownErrorToDomain
import com.javiersc.daggerHilt.data.remote.services.DataDragonService
import com.javiersc.daggerHilt.domain.models.Champion
import com.javiersc.daggerHilt.domain.models.Error
import com.javiersc.daggerHilt.domain.models.Region
import com.javiersc.daggerHilt.domain.repos.DataDragonRepo
import com.javiersc.resources.networkResponse.extensions.toResource
import com.javiersc.resources.resource.Resource
import com.javiersc.resources.resource.extensions.ifSuccess
import com.javiersc.resources.resource.extensions.toResourceSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DataDragonRepoImpl @Inject constructor(
    private val dataDragonService: DataDragonService,
    private val championsDAO: ChampionsDAO,
) : DataDragonRepo {

    override fun getChampions(): Flow<Resource<List<Champion>, Error>> = flow {
        emit(Resource.Loading)

        val championsResource = dataDragonService.getChampions("10.11.1", Region.EN_US.value).toResource(
            success = ChampionsDTO::toDomain,
            error = ::errorToDomain,
            unknownError = ::unknownErrorToDomain,
            internetNotAvailable = ::internetNotAvailableToDomain,
        )

        emit(championsResource)

        championsResource.ifSuccess { champions ->
            championsDAO.insertAll(champions.map(Champion::toEntity))
        }

        championsDAO.getAll().collect { championsEntity ->
            emit(championsEntity.map(ChampionEntity::toDomain).toResourceSuccess())
        }
    }
}
