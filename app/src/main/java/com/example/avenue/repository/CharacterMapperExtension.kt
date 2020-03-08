package com.example.avenue.repository

import android.location.Location
import com.example.avenue.database.entity.CharactersEntity
import com.example.avenue.remote.CharacterApiContract

fun CharactersEntity.toDomain(entity: CharactersEntity): CharacterApiContract.CharactersResults {
    return CharacterApiContract.CharactersResults(
        entity.id,
        entity.name,
        entity.status,
        entity.species,
        entity.gender,
        entity.image,
        CharacterApiContract.CharacterLocation(entity.location.name),
        entity.episode
    )
}