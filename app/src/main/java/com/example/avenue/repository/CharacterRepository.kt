package com.example.avenue.repository

import android.location.Location
import com.example.avenue.database.dao.CharacterDao
import com.example.avenue.database.entity.CharactersEntity
import com.example.avenue.remote.CharacterApi
import com.example.avenue.remote.CharacterApiContract
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val charactersDao: CharacterDao,
    private val charactersApi: CharacterApi
) {

    fun getCharacters(): Observable<List<CharacterApiContract.CharactersResults>> {
        return getCharactersFromDb().flatMapIterable { it }
            .map { it.toDomain(it) }
            .toList()
            .toObservable()

    }

    private fun getCharactersFromDb(): Observable<List<CharactersEntity>> {
        return charactersDao.getCharacters()
            .toObservable()
            .flatMap { list ->
                return@flatMap if (list.isEmpty()) {
                    getCharactersFromApi().toList().toObservable()
                } else {
                    Observable.just(list)
                }
            }
    }

    private fun getCharactersFromApi(): Observable<CharactersEntity> {
        return fetchInitialCharacters()
            .concatMap { listOfCharacters ->
                Observable.fromIterable(listOfCharacters).map { mappingFromApiToDb(it) }
            }
    }

    private fun fetchInitialCharacters(): Observable<List<CharacterApiContract.CharactersResults>> {
        return charactersApi.getCharacters()
            .subscribeOn(Schedulers.io())
            .concatMap { response ->
                if (response.characterPageInfo.next.isEmpty()) {
                    Observable.just(response.charactersResults)
                } else {
                    Observable.just(response.charactersResults)
                        .concatWith(fetchNextCharacters(response.characterPageInfo.next))
                }
            }
            .doOnNext {
                storeCharactersInDb(it)
            }
    }

    private fun fetchNextCharacters(nextUrl: String): Observable<List<CharacterApiContract.CharactersResults>> {
        return charactersApi.getNextCharacters(nextUrl)
            .subscribeOn(Schedulers.io())
            .concatMap { response ->
                if (response.characterPageInfo.next.isEmpty()) {
                    Observable.just(response.charactersResults)
                } else {
                    Observable.just(response.charactersResults)
                        .concatWith(fetchNextCharacters(response.characterPageInfo.next))
                }
            }
            .doOnNext {
                storeCharactersInDb(it)
            }
    }

    private fun storeCharactersInDb(characters: List<CharacterApiContract.CharactersResults>) {
        characters.forEach { charactersResults ->
            val mapped = mappingFromApiToDb(charactersResults)
            charactersDao.insert(mapped)
        }
    }

    private fun mappingFromApiToDb(charactersResults: CharacterApiContract.CharactersResults): CharactersEntity {
        return CharactersEntity(
            charactersResults.id,
            charactersResults.name,
            charactersResults.status,
            charactersResults.species,
            charactersResults.gender,
            charactersResults.image,
            charactersResults.location,
            charactersResults.episode
        )
    }

}