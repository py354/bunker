package com.example.bunker

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.bunker.databinding.ActivityMainMenuBinding

class MainMenuActivity : AppCompatActivity() {
    lateinit var bindging: ActivityMainMenuBinding
    private val dataModel: DataModel by viewModels()
    private val musicDataModel: MusicDataModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindging = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(bindging.root)
        supportFragmentManager.beginTransaction().replace(R.id.placeholder, MainMenuFragment()).commit()
        dataModel.message.observe(this) {
            supportFragmentManager.beginTransaction().replace(R.id.placeholder, it).commit()
        }
        musicDataModel.message.observe(this) {
            updateMusic()
        }
    }

    override fun onResume() {
        super.onResume()
        updateMusic()
    }

    override fun onPause() {
        super.onPause()
        stopService(Intent(this, MyService::class.java))
    }

    private fun updateMusic() {
        val i = Intent(this, MyService::class.java)

        val sharedPref = getSharedPreferences("settings", Context.MODE_PRIVATE)
        if (sharedPref.getBoolean("music", true)) {
            i.putExtra("volume", sharedPref.getInt("volume", 10))
        } else {
            i.putExtra("volume", 0)
        }
        startService(i)
    }
}