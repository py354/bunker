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
//            val i = Intent(this@MainMenuActivity, SettingsActivity::class.java)
//            startActivity(i)
            supportFragmentManager.beginTransaction().replace(R.id.menu_placeholder, SettingsFragment()).commit()
        }
    }

    override fun onResume() {
        super.onResume()
        startService(Intent(this, MyService::class.java))
    }

    override fun onPause() {
        super.onPause()
        stopService(Intent(this, MyService::class.java))
    }
}