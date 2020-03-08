package com.example.avenue.dagger.modules

import com.example.avenue.activity.LauncherActivity
import com.example.avenue.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun launcherActivity(): LauncherActivity

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivity(): MainActivity

}