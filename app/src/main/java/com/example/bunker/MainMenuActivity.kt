package com.example.bunker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.bunker.databinding.ActivityMainMenuBinding

class MainMenuActivity : AppCompatActivity() {
    lateinit var bindging: ActivityMainMenuBinding
    private val dataModel: DataModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindging = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(bindging.root)
        supportFragmentManager.beginTransaction().replace(R.id.placeholder, MainMenuFragment()).commit()
        dataModel.message.observe(this) {
            supportFragmentManager.beginTransaction().replace(R.id.placeholder, it).commit()
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