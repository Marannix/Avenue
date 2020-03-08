package com.example.dashboard.dagger.module

import androidx.fragment.app.FragmentActivity
import com.example.dashboard.activity.DashboardActivity
import dagger.Binds
import dagger.Module

@Module
abstract class DashboardActivityModule {

    @Binds
    abstract fun fragmentActivity(activity: DashboardActivity): FragmentActivity

}