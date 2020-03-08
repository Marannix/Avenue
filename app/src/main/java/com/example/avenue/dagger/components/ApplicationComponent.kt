package com.example.avenue.dagger.components

import android.app.Application
import com.example.avenue.MainApplication
import com.example.avenue.dagger.modules.*
import com.example.avenue.dagger.modules.navigator.DashboardNavigatorModule
import com.example.dashboard.dagger.module.DashboardActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ActivityBindingModule::class,
        ApiModule::class,
        RoomModule::class,
        ApplicationModule::class,
        ViewModelModule::class,
        DashboardActivityModule::class,
        DashboardNavigatorModule::class,
        AndroidSupportInjectionModule::class]
)
interface ApplicationComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    override fun inject(application: MainApplication)
}