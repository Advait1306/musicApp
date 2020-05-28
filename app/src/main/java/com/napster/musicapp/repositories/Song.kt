package com.napster.musicapp.repositories

import android.net.Uri


class Song() {
    var title: String? = null
    var album: String? = null
    var artist: String? = null
    var albumArt: Uri? = null
    var duration: String? = null
    var location: Uri? = null
    var id: Long = 0
}