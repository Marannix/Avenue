package com.example.avenue.dagger.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.avenue.dagger.ViewModelFactory
import com.example.avenue.dagger.ViewModelKey
import com.example.avenue.viewmodel.CharactersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    internal abstract fun bindingCharactersViewModel(viewModel: CharactersViewModel): ViewModel

}