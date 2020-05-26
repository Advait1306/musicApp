package com.napster.musicapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Song::class), version = 1)
abstract class SongDatabase : RoomDatabase() {

    abstract fun songDAO(): SongDAO



    companion object {

        @Volatile
        private var INSTANCE: SongDatabase? = null

        fun getDatabase(context: Context): SongDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            } else {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        SongDatabase::class.java,
                        "song"
                    ).build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
    }
}





                }




}
