package com.napster.musicapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SongDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(song : Song)

    @Query("DELETE FROM song")
    fun deleteAll()

    @Query("SELECT * from song ORDER BY songName ASC ")
    fun getAllData(): LiveData<List<Song>>


}