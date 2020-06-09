package com.javiersc.daggerHilt.data.remote.models

import domain.models.Info
import kotlinx.serialization.Serializable

@Serializable
data class InfoDTO(
    val attack: Int,
    val defense: Int,
    val magic: Int,
    val difficulty: Int,
)

fun InfoDTO.toDomain() = Info(
    attack = attack,
    defense = defense,
    magic = magic,
    difficulty = difficulty
)
