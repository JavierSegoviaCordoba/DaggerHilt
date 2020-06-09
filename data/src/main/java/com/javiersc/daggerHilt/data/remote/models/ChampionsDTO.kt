package com.javiersc.daggerHilt.data.remote.models

import com.javiersc.resources.networkResponse.Headers
import domain.models.Champion
import kotlinx.serialization.Serializable

@Serializable
data class ChampionsDTO(
    val type: String,
    val format: String,
    val version: String,
    val data: Map<String, ChampionDTO>,
)

fun ChampionsDTO.toDomain(code: Int, headers: Headers): List<Champion> = data.values.map(ChampionDTO::toDomain)
