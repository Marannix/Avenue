package com.example.avenue.usecase

import com.example.avenue.remote.CharacterApiContract
import com.example.avenue.repository.CharacterRepository
import io.reactivex.Observable
import javax.inject.Inject

class CharacterUseCase @Inject constructor(
    private val charactersRepository: CharacterRepository
) {

    fun getCharacterDataState(): Observable<CharacterDataState> {
        return charactersRepository.getCharacters()
            .map<CharacterDataState> { listOfCharacters ->
                CharacterDataState.Success(listOfCharacters)
            }
            .onErrorReturn { error -> CharacterDataState.Error(error.message) }
    }

}

sealed class CharacterDataState {
    data class Success(val characters: List<CharacterApiContract.CharactersResults>) : CharacterDataState()
    data class Error(val errorMessage: String?) : CharacterDataState()
}