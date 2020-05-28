package com.napster.musicapp.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.napster.musicapp.repositories.Song

class PlayerViewModel: ViewModel() {

    private val TAG = "PLAYER VIEW MODEL"
    private var song = MutableLiveData<Song>()

    fun getCurrentSongLiveData():LiveData<Song>{
        return song
    }

    fun setCurrentSong(newSong: Song?){
        song.value = newSong
    }
}