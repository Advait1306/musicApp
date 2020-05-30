package com.napster.musicapp

import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.napster.musicapp.adapters.SongRecyclerViewAdapter
import com.napster.musicapp.fragments.PlayerFragment
import com.napster.musicapp.viewModels.PlayerViewModel
import com.napster.musicapp.viewModels.SongListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import android.Manifest.permission.READ_EXTERNAL_STORAGE as re

class MainActivity : AppCompatActivity() {

    private val TAG = "MAIN ACTIVITY"
    private val songListViewModel: SongListViewModel by viewModels()
    private val playerViewModel: PlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentHolder, PlayerFragment())
        fragmentTransaction.commit()

        //RecyclerView code
        val recyclerViewAdapter =
            SongRecyclerViewAdapter(songListViewModel.getSongList(), playerViewModel)
        val recyclerViewLayoutManager = LinearLayoutManager(this)

        songRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = recyclerViewLayoutManager
            adapter = recyclerViewAdapter
        }

        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }

        //Observing liveData for changes in the database of songs
        songListViewModel.getSongList().observe(this, Observer {
            recyclerViewAdapter.notifyDataSetChanged()
        })

        //Checking permissions and then getting song list
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
        songListViewModel.locateAudioFilesOnDevice()
    }

}