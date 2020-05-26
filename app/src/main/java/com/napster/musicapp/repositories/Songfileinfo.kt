package com.napster.musicapp.repositories

import android.os.Parcel


class Songfileinfo(`in`: Parcel?) {
    var title: String? = null
    var album: String? = null
    var artist: String? = null
    var fileuri: String? = null
    var album_art: String? = null
    var duration: String? = null
    var id: Long = 0

    fun Songfileinfo(`in`: Parcel) {
        title = `in`.readString()
        album = `in`.readString()
        artist = `in`.readString()
        fileuri = `in`.readString()
        album_art = `in`.readString()
        id = `in`.readLong()
    }
}