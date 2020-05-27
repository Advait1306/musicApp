package com.napster.musicapp.viewModels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.napster.musicapp.repositories.Song
import com.napster.musicapp.repositories.SongRepository

class SongListViewModel(application: Application): AndroidViewModel(application){

    private val songRepository = SongRepository.getInstance(application.applicationContext)
    private val songList = songRepository.getSongsList()

    fun getSongList(): LiveData<ArrayList<Song>>{
        return songList
    }

    fun locateAudioFilesOnDevice(){
        songRepository.locateAudioFilesOnDevice()
    }
}