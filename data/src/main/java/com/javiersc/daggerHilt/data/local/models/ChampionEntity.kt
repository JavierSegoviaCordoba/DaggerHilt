package com.javiersc.daggerHilt.data.local.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.javiersc.daggerHilt.domain.models.Champion
import com.javiersc.daggerHilt.domain.models.ChampionId
import com.javiersc.daggerHilt.domain.models.ChampionKey
import com.javiersc.daggerHilt.domain.models.Info

@Entity(tableName = "champion")
data class ChampionEntity(
    @PrimaryKey val id: String,
    val key: Int,
    val name: String,
    val title: String,
    val blurb: String,
    @Embedded val info: Info,
    val image: String,
    val tags: List<String>,
)

fun Champion.toEntity(): ChampionEntity = ChampionEntity(
    id = id.id,
    key = key.key,
    name = name,
    title = title,
    blurb = blurb,
    info = info,
    image = image,
    tags = tags,
)

fun ChampionEntity.toDomain(): Champion =
    Champion(
        id = ChampionId(id),
        key = ChampionKey(key),
        name = name,
        title = title,
        blurb = blurb,
        info = info,
        image = image,
        tags = tags
    )
