package com.javiersc.daggerHilt.data.remote.models

import com.javiersc.resources.networkResponse.Headers
import domain.models.Champion
import domain.models.ChampionId
import domain.models.ChampionKey
import kotlinx.serialization.Serializable

@Serializable
data class ChampionsDTO(
    val type: String,
    val format: String,
    val version: String,
    val data: Map<String, ChampionDTO>,
)

fun ChampionsDTO.toDomain(code: Int, headers: Headers): List<Champion> = data.values.map { championDTO: ChampionDTO ->
    Champion(
        id = ChampionId(championDTO.id),
        key = ChampionKey(championDTO.key.toInt()),
        name = championDTO.name,
        title = championDTO.title,
        blurb = championDTO.blurb,
        info = championDTO.info.toDomain(),
        image = championDTO.image.toDomain(),
        tags = championDTO.tags,
    )
}
