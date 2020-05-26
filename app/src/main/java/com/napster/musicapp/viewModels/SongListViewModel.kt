package com.napster.musicapp.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.napster.musicapp.repositories.Song
import com.napster.musicapp.repositories.SongRepository

class SongListViewModel(context: Context): ViewModel(){

    private val songRepository = SongRepository(context)
    private val songList = songRepository.getSongsList()

    fun getSongList(): LiveData<ArrayList<Song>>{
        return songList
    }

}