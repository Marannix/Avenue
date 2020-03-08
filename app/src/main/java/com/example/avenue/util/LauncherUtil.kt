package com.example.avenue.util

import android.app.Activity
import android.content.Intent
import com.example.avenue.core.AppNavigator
import javax.inject.Inject

class LauncherUtil @Inject constructor(
    private val appNavigator: AppNavigator
){

    fun handleIntent(activity: Activity): Intent {
        return createDashboardIntent(activity)
    }

    private fun createDashboardIntent(activity: Activity) : Intent {
        return appNavigator.dashboardNavigator.getIntent(activity)
    }
}