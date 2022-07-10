package com.example.bunker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bunker.databinding.ActivityMainMenuBinding

class MainMenuActivity : AppCompatActivity() {
    lateinit var bindging: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindging = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(bindging.root)

        bindging.settings.setOnClickListener {
            val i = Intent(this@MainMenuActivity, SettingsActivity::class.java)
            startActivity(i)
        }
    }
}