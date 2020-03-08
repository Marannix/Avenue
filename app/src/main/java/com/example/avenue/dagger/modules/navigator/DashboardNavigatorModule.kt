package com.example.avenue.dagger.modules.navigator

import android.content.Context
import android.content.Intent
import com.example.avenue.core.AppNavigator
import com.example.dashboard.activity.DashboardActivity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

//TODO Rename dashboard to home?
@Module
class DashboardNavigatorModule {

    @Provides
    @Singleton
    fun onboardingNavigator(): AppNavigator.DashboardNavigator {
        return object : AppNavigator.DashboardNavigator() {
            override fun open(context: Context) {
                val intent = Intent(context, DashboardActivity::class.java)
                context.startActivity(intent)
            }

            override fun getIntent(context: Context): Intent {
                return Intent(context, DashboardActivity::class.java)
            }
        }
    }
}