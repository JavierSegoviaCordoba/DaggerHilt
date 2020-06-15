package com.javiersc.daggerHilt.data.remote.models

import com.javiersc.daggerHilt.domain.models.Champion
import com.javiersc.daggerHilt.domain.models.ChampionId
import com.javiersc.daggerHilt.domain.models.ChampionKey
import kotlinx.serialization.Serializable

@Serializable
data class ChampionDTO(
    val version: String,
    val id: String,
    val key: String,
    val name: String,
    val title: String,
    val blurb: String,
    val info: InfoDTO,
    val image: ImageDTO,
    val tags: List<String>,
    val partype: String,
    val stats: StatsDTO,
)

fun ChampionDTO.toDomain(): Champion =
    Champion(
        id = ChampionId(id),
        key = ChampionKey(key.toInt()),
        name = name,
        title = title,
        blurb = blurb,
        info = info.toDomain(),
        image = image.toDomain(),
        tags = tags,
    )
