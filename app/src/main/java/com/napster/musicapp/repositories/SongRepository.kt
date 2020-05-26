package com.napster.musicapp.repositories

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log



class SongRepository {
    var songList: ArrayList<Song> = arrayListOf()
    private val albumArtUri: Uri = Uri.parse("content://media/external/audio/albumart")

    private val TAG = "SONG REPOSITORY"

    fun getPlayList(context: Context){
        try {
            val metaData: Array<String> = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ALBUM_ID
            )


            val audioCursor: Cursor? = context.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, metaData, "", null, "")

            if (audioCursor != null) {
                if (audioCursor.moveToFirst()) {
                    do {
                        val audioTitle = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
                        val audioArtist = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
                        val audioDuration = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
                        val audioAlbum = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
                        val audioAlbumId = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)
                        val songId = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)

                        val song = Song()

                        song.title = audioCursor.getString(audioTitle)
                        song.duration = (audioCursor.getString(audioDuration))
                        song.artist = audioCursor.getString(audioArtist)
                        song.album = (audioCursor.getString(audioAlbum))
                        song.id = audioCursor.getLong(songId)
                        song.albumArt = (ContentUris.withAppendedId(albumArtUri, audioCursor.getLong(audioAlbumId))).toString()

                        songList.add(song)
                    } while (audioCursor.moveToNext())
                }
            }
            audioCursor?.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }

        for(song in songList){
            Log.d(TAG, "${song.title}")
        }
    }

}

