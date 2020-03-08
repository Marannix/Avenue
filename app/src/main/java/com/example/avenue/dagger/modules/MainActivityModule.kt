package com.example.avenue.dagger.modules

import androidx.fragment.app.FragmentActivity
import com.example.avenue.activity.MainActivity
import com.example.avenue.fragment.CharacterFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun provideMainActivity(activity: MainActivity): FragmentActivity

    @ContributesAndroidInjector
    abstract fun charactersFragment(): CharacterFragment
}