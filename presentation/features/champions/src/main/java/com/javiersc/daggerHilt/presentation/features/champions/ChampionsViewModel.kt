package com.javiersc.daggerHilt.presentation.features.champions

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.javiersc.resources.core.extensions.viewModel.launch
import com.javiersc.resources.resource.Resource
import domain.GetChampionsUseCase
import domain.models.Champion
import domain.models.Error
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

class ChampionsViewModel @ViewModelInject constructor(private val championsUseCase: GetChampionsUseCase) : ViewModel() {

    private val _champions: MutableStateFlow<Resource<List<Champion>, Error>> = MutableStateFlow(Resource.Loading)
    val champions: StateFlow<Resource<List<Champion>, Error>> = _champions

    init {
        getChampions()
    }

    fun getChampions() = launch {
        championsUseCase().collect { _champions.value = it }
    }
}
