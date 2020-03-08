package com.example.avenue.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import com.example.avenue.R
import com.example.avenue.util.LauncherUtil
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LauncherActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var launcherUtil: LauncherUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        continueToNextIntent()

    }

    private fun continueToNextIntent() {
        val nextIntent = launcherUtil.handleIntent(this@LauncherActivity)
        launchNextActivity(nextIntent)
        finish()
    }

    private fun launchNextActivity(intent: Intent) {
        startActivity(intent)
        finish()
    }
}
