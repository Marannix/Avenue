package com.example.avenue.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.avenue.database.entity.CharactersEntity
import io.reactivex.Single

@Dao
abstract class CharacterDao : EntityDao<CharactersEntity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract override fun insert(entity: CharactersEntity): Long

    @Query("select * from characters")
    abstract fun getCharacters(): Single<List<CharactersEntity>>
}