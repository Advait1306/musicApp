package com.napster.musicapp.repositories

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class SongRepository(context: Context) {
    private var songList: ArrayList<Song> = arrayListOf()
    private var songListLiveData = MutableLiveData<ArrayList<Song>>()
    private val albumArtUri: Uri = Uri.parse("content://media/external/audio/albumart")
    private val TAG = "SONG REPOSITORY"

    init {
        try {
            val metaData: Array<String> = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ALBUM_ID
            )


            context.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, metaData, "", null, "").use{

                val audioTitle = it!!.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
                val audioArtist = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
                val audioDuration = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
                val audioAlbum = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
                val audioAlbumId = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)
                val songId = it.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)

                while (it.moveToNext()){
                    val song = Song()
                    song.title = it.getString(audioTitle)
                    song.duration = it.getString(audioDuration)
                    song.artist = it.getString(audioArtist)
                    song.album = it.getString(audioAlbum)
                    song.id = it.getLong(songId)
                    song.location = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, it.getLong(songId))
                    song.albumArt = ContentUris.withAppendedId(albumArtUri, it.getLong(audioAlbumId))
                    songList.add(song)
                    songListLiveData.value = songList
                }

            }

        } catch (e: Exception) {
            Log.e(TAG, "error")
            e.printStackTrace()
        }

        for(song in songList){
            Log.d(TAG, "${song.location} ${song.title}")
        }
    }

    fun getSongsList(): LiveData<ArrayList<Song>> {
        return songListLiveData
    }

}
