package com.napster.musicapp

import android.content.pm.PackageManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.Manifest.permission.READ_EXTERNAL_STORAGE as re
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import com.napster.musicapp.viewModels.SongListViewModel

class MainActivity : AppCompatActivity() {

    private val TAG = "MAIN ACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mediaPlayer: MediaPlayer? = null

        play.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(applicationContext, R.raw.song)
            }
            mediaPlayer!!.start()
        }
        if (checkSelfPermission(re) == PackageManager.PERMISSION_GRANTED) {
            getSongs()
        } else {
            requestPermissions(arrayOf(re), 12345)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if ((grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED)
        ) {
            getSongs()
        } else {
            Log.d(TAG, "Permission denied")
        }
    }

    private fun getSongs() {
        val songListViewModel = SongListViewModel(applicationContext)
    }

}