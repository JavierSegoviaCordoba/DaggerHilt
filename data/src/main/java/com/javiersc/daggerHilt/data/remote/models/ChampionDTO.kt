package com.javiersc.daggerHilt.data.remote.models

import domain.models.Champion
import domain.models.ChampionId
import domain.models.ChampionKey
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

fun ChampionDTO.toDomain(): Champion = Champion(
    id = ChampionId(id),
    key = ChampionKey(key.toInt()),
    name = name,
    title = title,
    blurb = blurb,
    info = info.toDomain(),
    image = image.toDomain(),
    tags = tags,
)
