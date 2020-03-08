package com.example.dashboard.dagger.module

import com.example.dashboard.activity.DashboardActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DashboardBindingActivityModule {

    @ContributesAndroidInjector(modules = [DashboardActivityModule::class])
    abstract fun contributeMainActivity(): DashboardActivity

}