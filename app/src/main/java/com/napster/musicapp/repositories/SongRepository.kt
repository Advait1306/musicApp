package com.napster.musicapp.repositories

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log

class SongRepository {
    //Get audio  files and details from device
    fun audioRet() {
        val proj: Array<String> = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ALBUM_ID
        )
        val context : Context? = null
        val audioCursor: Cursor? = context?.contentResolver
            ?.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, proj, null, null,null )
        if (audioCursor != null) {
            if (audioCursor.moveToFirst()) {
                do {
                    val audioTitle = audioCursor . getColumnIndexOrThrow (MediaStore.Audio.Media.TITLE)
                    val audioartist = audioCursor . getColumnIndexOrThrow (MediaStore.Audio.Media.ARTIST)
                    val audioduration = audioCursor . getColumnIndexOrThrow (MediaStore.Audio.Media.DURATION)
                    val audiodata = audioCursor . getColumnIndexOrThrow (MediaStore.Audio.Media.DATA)
                    val audioalbum = audioCursor . getColumnIndexOrThrow (MediaStore.Audio.Media.ALBUM)
                    val audioalbumid = audioCursor . getColumnIndexOrThrow (MediaStore.Audio.Media.ALBUM_ID)
                    val song_id = audioCursor . getColumnIndexOrThrow (MediaStore.Audio.Media._ID)
                    val info = Songfileinfo(null)
                    val albumArtUri:Uri = Uri.parse("content://media/external/audio/albumart")
                    info.setFile_uri(audioCursor.getString(audiodata))
                    info.setTitle(audioCursor.getString(audioTitle))
                    info.setDuration((audioCursor.getString(audioduration)))
                    info.setArtist(audioCursor.getString(audioartist))
                    info.setAlbum(audioCursor.getString(audioalbum))
                    info.setId(audioCursor.getLong(song_id))
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
    }
}
fun Songfileinfo.setAlbum(string: String?): String? {
return album
}
fun Songfileinfo.setId(long: Long): Long {
return id
}
fun Songfileinfo.setAlbum_art(toString: String): String? {
return album_art
}
fun Songfileinfo.setArtist(string: String?): String? {
return artist
}
fun Songfileinfo.setDuration(string: String?): String? {
return duration
}
fun Songfileinfo.setFile_uri(string: String?): String? {
return fileuri
}
fun Songfileinfo.setTitle(string: String?): String? {
return title
}