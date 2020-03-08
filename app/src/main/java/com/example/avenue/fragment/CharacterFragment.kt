package com.example.avenue.fragment


import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.avenue.R
import com.example.avenue.common.exhaustive
import com.example.avenue.viewmodel.CharactersViewModel
import io.reactivex.disposables.CompositeDisposable

class CharacterFragment : BaseFragment() {

    private lateinit var viewmodel: CharactersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(CharactersViewModel::class.java)


        viewmodel.getCharacters()

        viewmodel.viewState.observe(this, Observer {
            when (it) {
                is CharactersViewModel.CharacterViewState.Loading -> {
                    Log.d("Loading", "hi")
                }

                is CharactersViewModel.CharacterViewState.ShowCharacters -> {
                    Log.d("Characters", it.characters.toString())
                }

                is CharactersViewModel.CharacterViewState.ShowError -> {
                    Log.d("Error", it.errorMessage)
                }
            }.exhaustive
        })
    }


}
