package com.javiersc.daggerHilt.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatsDTO(
    val hp: Double,
    @SerialName("hpperlevel") val hpPerLevel: Double,
    val mp: Double,
    @SerialName("mpperlevel") val mpPerLevel: Double,
    @SerialName("movespeed") val moveSpeed: Double,
    val armor: Double,
    @SerialName("armorperlevel") val armorPerLevel: Double,
    @SerialName("spellblock") val spellBlock: Double,
    @SerialName("spellblockperlevel") val spellBlockPerLevel: Double,
    @SerialName("attackrange") val attackRange: Double,
    @SerialName("hpregen") val hpRegen: Double,
    @SerialName("hpregenperlevel") val hpRegenPerLevel: Double,
    @SerialName("mpregen") val mpRegen: Double,
    @SerialName("mpregenperlevel") val mpRegenPerLevel: Double,
    @SerialName("crit") val crit: Double,
    @SerialName("critperlevel") val critPerLevel: Double,
    @SerialName("attackdamage") val attackDamage: Double,
    @SerialName("attackdamageperlevel") val attackDamagePerLevel: Double,
    @SerialName("attackspeedperlevel") val attackSpeedPerLevel: Double,
    @SerialName("attackspeed") val attackSpeed: Double,
)
