package com.napster.musicapp

import android.content.pm.PackageManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.Manifest.permission.READ_EXTERNAL_STORAGE as re
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.napster.musicapp.adapters.SongRecyclerViewAdapter
import com.napster.musicapp.fragments.PlayerFragment
import com.napster.musicapp.viewModels.PlayerViewModel
import kotlinx.android.synthetic.main.activity_main.*
import com.napster.musicapp.viewModels.SongListViewModel

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
        val recyclerViewAdapter = SongRecyclerViewAdapter(songListViewModel.getSongList(), playerViewModel)
        val recyclerViewLayoutManager = LinearLayoutManager(this )

        songRecyclerView.apply{
            setHasFixedSize(true)
            layoutManager = recyclerViewLayoutManager
            adapter = recyclerViewAdapter
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