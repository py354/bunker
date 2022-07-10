package com.example.bunker

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast


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

    override fun onDestroy() {
        player!!.stop()
        super.onDestroy()
    }
}