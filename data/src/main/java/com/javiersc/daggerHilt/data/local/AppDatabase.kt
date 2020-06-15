package com.javiersc.daggerHilt.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.javiersc.daggerHilt.data.local.converters.ListStringConverter
import com.javiersc.daggerHilt.data.local.daos.ChampionsDAO
import com.javiersc.daggerHilt.data.local.models.ChampionEntity

@Database(entities = [ChampionEntity::class], version = 1)
@TypeConverters(value = [ListStringConverter::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun championsDao(): ChampionsDAO

    companion object {
        operator fun invoke(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
