package com.example.avenue.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.avenue.remote.CharacterApiContract
import com.example.avenue.usecase.CharacterDataState
import com.example.avenue.usecase.CharacterUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase
) : BaseViewModel() {

    val viewState = MutableLiveData<CharacterViewState>()

    fun getCharacters() {
        characterUseCase.getCharacterDataState()
            .observeOn(AndroidSchedulers.mainThread())
            .map { characterDataState ->
                return@map when (characterDataState) {
                    is CharacterDataState.Success -> {
                        CharacterViewState.ShowCharacters(characterDataState.characters)
                    }
                    is CharacterDataState.Error -> {
                            CharacterViewState.ShowError(characterDataState.errorMessage)
                    }
                }
            }
            .doOnSubscribe { viewState.value = CharacterViewState.Loading }
            .subscribe { viewState ->
                this.viewState.value = viewState
            }.addDisposable()
    }

    sealed class CharacterViewState {
        object Loading : CharacterViewState()
        data class ShowCharacters(val characters: List<CharacterApiContract.CharactersResults>) : CharacterViewState()
        data class ShowError(val errorMessage: String?) : CharacterViewState()
    }
}