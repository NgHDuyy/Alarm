package com.example.alarm

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class AlarmSound : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private lateinit var mediaPlayer: MediaPlayer
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if (intent.getStringExtra(status).equals("on")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.bao_thuc)
            mediaPlayer.start()
        } else if (intent.getStringExtra(status).equals("off")) {
            mediaPlayer.release()
        }
        return START_NOT_STICKY
    }
}