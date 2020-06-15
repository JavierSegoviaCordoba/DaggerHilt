package com.javiersc.daggerHilt.domain.repos

import com.javiersc.daggerHilt.domain.models.Champion
import com.javiersc.daggerHilt.domain.models.Error
import com.javiersc.resources.resource.Resource
import kotlinx.coroutines.flow.Flow

interface DataDragonRepo {
    fun getChampions(): Flow<Resource<List<Champion>, Error>>
}
