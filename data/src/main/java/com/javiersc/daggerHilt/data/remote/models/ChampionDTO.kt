package com.javiersc.daggerHilt.data.remote.models

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
