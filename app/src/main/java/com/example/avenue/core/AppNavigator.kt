package com.example.avenue.core

import android.content.Context
import android.content.Intent
import javax.inject.Inject


class AppNavigator @Inject constructor(
    val dashboardNavigator: DashboardNavigator
) {

    interface Navigator {
        fun open(context: Context)
        fun getIntent(context: Context): Intent
    }

    abstract class DashboardNavigator : Navigator

}
