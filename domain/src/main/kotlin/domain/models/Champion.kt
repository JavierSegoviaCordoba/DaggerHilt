package domain.models

data class Champion(
    val id: ChampionId,
    val key: ChampionKey,
    val name: String,
    val title: String,
    val blurb: String,
    val info: Info,
    val image: String,
    val tags: List<String>
)

inline class ChampionId(val id: String)

inline class ChampionKey(val key: Int)

class Dog(val name: String)
