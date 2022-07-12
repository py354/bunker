package com.example.bunker

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels


class MyService : Service() {
    var player: MediaPlayer? = null

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        player = MediaPlayer.create(this, R.raw.sample)
        player!!.isLooping = true
        player!!.start()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val volume = intent?.getIntExtra("volume", 10)
        if (volume != null) {
            val fv = volume / 10.0f
            player?.setVolume(fv, fv)
        }
        return 0
    }

    override fun onDestroy() {
        player!!.stop()
        super.onDestroy()
    }
}