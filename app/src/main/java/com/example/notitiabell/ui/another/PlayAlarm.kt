package com.example.notitiabell.ui.another

import android.content.Context
import android.media.MediaPlayer
import com.example.notitiabell.R


class PlayAlarm {

    companion object {
        private var mediaPlayer: MediaPlayer? = null

        fun playAudio(context: Context) {
            mediaPlayer = MediaPlayer.create(context,
                R.raw.alarm
            )
            mediaPlayer?.start()
        }

        fun stopAudio() {
            mediaPlayer!!.stop()
        }
    }



}