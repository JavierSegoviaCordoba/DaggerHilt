package com.javiersc.daggerHilt.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.javiersc.daggerHilt.data.local.models.ChampionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChampionsDAO {

    @Query("SELECT * FROM champion")
    fun getAll(): Flow<List<ChampionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(champions: List<ChampionEntity>)
}
