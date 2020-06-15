package com.javiersc.daggerHilt.domain.useCases

import com.javiersc.daggerHilt.domain.models.Champion
import com.javiersc.daggerHilt.domain.models.Error
import com.javiersc.daggerHilt.domain.repos.DataDragonRepo
import com.javiersc.resources.resource.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChampionsUseCase @Inject constructor(private val dataDragonRepo: DataDragonRepo) {
    operator fun invoke(): Flow<Resource<List<Champion>, Error>> = dataDragonRepo.getChampions()
}
