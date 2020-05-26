package com.napster.musicapp

import android.Manifest.permission.READ_EXTERNAL_STORAGE as re
import android.os.Bundle
import android.os.Parcel
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.napster.musicapp.repositories.songList
import com.napster.musicapp.repositories.SongRepository
import kotlin.properties.Delegates
import com.napster.musicapp.repositories.Songdata
import com.napster.musicapp.repositories.Songfileinfo

class MainActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val songfileinfo :Songfileinfo = Songfileinfo(Parcel.obtain())
        SongRepository().audioRet()
        Songdata().getPlayList(this)
        var perm by Delegates.notNull<Int>()
        requestPermissions(arrayOf(re),12345)
        perm = checkSelfPermission(re)
        Log.d("SHERE","${songfileinfo.title}")
        Log.d("HERE", "$songList")
        Log.d("THERE",perm.toString())

    }
}
