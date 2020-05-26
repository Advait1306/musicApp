package com.napster.musicapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Song")
class Song (

    @PrimaryKey(autoGenerate = true)
    private var id: Int,

    @ColumnInfo(name = "songName")
    private var songName : String,

    @ColumnInfo(name = "albumName")
    private var albumName: String,

    @ColumnInfo(name = "artist")
    private var artist : String,

    @ColumnInfo(name = "songDuration")
    private var songDuration : Int // in seconds
    )
{

    fun getId(): Int{
        return id
    }

    fun getSongName(): String{
        return songName
    }

    fun getAlbumName(): String{
        return albumName
    }

    fun getArtist(): String{
        return artist
    }

    fun getSongDuration(): Int{
        return songDuration
    }

}