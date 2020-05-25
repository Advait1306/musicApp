package com.napster.musicapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Song")
class Song (

    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "songName")
    private  var songName : String,

    @ColumnInfo(name = "albumName")
    private var albumName: String,

    @ColumnInfo(name = "artist")
    private  var artist : String,

    @ColumnInfo(name = "songDuration")
    private  var songDuration : Int // in seconds
    )
{

}