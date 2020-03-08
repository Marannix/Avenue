package com.example.avenue.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.avenue.R
import com.example.avenue.fragment.CharacterFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initCharacterFragment()
    }

    private fun initCharacterFragment() {
        supportFragmentManager.createFragment(
            fragmentId = R.id.fragmentContainer,
            factory = { CharacterFragment()},
            initializer = {})
    }

}
