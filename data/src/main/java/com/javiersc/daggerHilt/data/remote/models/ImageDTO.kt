package com.javiersc.daggerHilt.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class ImageDTO(
    val full: String,
    val sprite: String,
    val group: String,
    val x: Int,
    val y: Int,
    val w: Int,
    val h: Int,
)

fun ImageDTO.toDomain(): String = full
