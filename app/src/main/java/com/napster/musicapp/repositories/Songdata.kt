package com.napster.musicapp.repositories

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.napster.musicapp.*

var songList : ArrayList<Any>? = arrayListOf()
class Songdata {

    private val albumArtUri :Uri= Uri.parse("content://media/external/audio/albumart")

    fun getPlayList(context :Context): ArrayList<Any>? {
        run {

            try {
                val proj: Array<String> = arrayOf(
                    MediaStore.Audio.Media._ID,
                    MediaStore.Audio.Media.TITLE,
                    MediaStore.Audio.Media.ARTIST,
                    MediaStore.Audio.Media.DURATION,
                    MediaStore.Audio.Media.DATA,
                    MediaStore.Audio.Media.ALBUM,
                    MediaStore.Audio.Media.ALBUM_ID
                )


                val audioCursor: Cursor? = context.contentResolver.query(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    proj,"",null,"")

                if (audioCursor != null) {
                    if (audioCursor.moveToFirst()) {
                        do {
                            val audioTitle =
                                audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
                            val audioartist =
                                audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
                            val audioduration =
                                audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
                            /*val audiodata =
                                audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)*/
                            val audioalbum =
                                audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
                            val audioalbumid =
                                audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)
                            val songid =
                                audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
                            val info = Songfileinfo(null)
                            //info.setFile_uri(audioCursor.getString(audiodata))
                            info.setTitle(audioCursor.getString(audioTitle))
                            info.setDuration((audioCursor.getString(audioduration)))
                            info.setArtist(audioCursor.getString(audioartist))
                            info.setAlbum(audioCursor.getString(audioalbum))
                            info.setId(audioCursor.getLong(songid))
                            info.setAlbum_art(
                                (ContentUris.withAppendedId(
                                    albumArtUri,
                                    audioCursor.getLong(audioalbumid)
                                )).toString()
                            )
                            songList?.add(info)
                        } while (audioCursor.moveToNext())
                    }
                }
                audioCursor != null
                audioCursor?.close()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
Log.d("WHERE", "$songList")
        return songList
    }

}
/*
private fun ContentResolver.query(externalContentUri: Uri?, proj: () -> String, selection: String?, selectionArgs: Array<String>?, nothing: Nothing?): Cursor? {

}
*/