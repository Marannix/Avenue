package com.example.avenue.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.avenue.database.dao.CharacterDao
import com.example.avenue.database.entity.CharactersEntity

@Database(
    entities = [CharactersEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharacterDao
}