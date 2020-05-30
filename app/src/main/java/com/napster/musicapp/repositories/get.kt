package com.napster.musicapp.repositories

import androidx.lifecycle.LiveData
import com.napster.musicapp.adapters.SongRecyclerViewAdapter
import com.napster.musicapp.viewModels.PlayerViewModel
import kotlinx.android.synthetic.main.song_list_view.view.*

class get{
    fun milliSecondsToTimer(milliseconds:Long):String {

        val hours = (milliseconds / (1000 * 60 * 60)).toInt()
        val minutes = ((milliseconds % (1000 * 60 * 60)) / (1000 * 60)).toInt()
        val seconds = ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000).toInt()

        val sec = if (seconds > 10) {
            "$seconds"
        } else {
            "0$seconds"
        }
        val min = if (minutes > 10) {
            "$minutes"
        } else {
            "0$minutes"
        }
        val hour = if (hours > 10) {
            "$hours"
        } else {
            "0$hours"
        }

        // return timer string
        return if (hours > 0) {
            "$hour : $min : $sec"
        } else {
            "$min : $sec"
        }
    }

}