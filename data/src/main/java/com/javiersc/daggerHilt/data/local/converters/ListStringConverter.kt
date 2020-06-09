package com.javiersc.daggerHilt.data.local.converters

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import kotlinx.serialization.parseList
import kotlinx.serialization.stringify

class ListStringConverter {

    @TypeConverter
    fun fromListToString(list: List<String>): String = Json.stringify(list)

    @TypeConverter
    fun fromStringToList(json: String): List<String> = Json.parseList(json)
}
