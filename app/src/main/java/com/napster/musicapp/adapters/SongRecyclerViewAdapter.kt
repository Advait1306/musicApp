package com.napster.musicapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.napster.musicapp.R
import com.napster.musicapp.repositories.Song
import com.napster.musicapp.viewModels.PlayerViewModel
import kotlinx.android.synthetic.main.song_list_view.view.*
import kotlin.properties.Delegates


class SongRecyclerViewAdapter(private val songList: LiveData<ArrayList<Song>>, private val playerViewModel: PlayerViewModel) :
    RecyclerView.Adapter<SongRecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.song_list_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (songList.value != null)
            songList.value!!.size
        else
            0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentSong = songList.value!![position]
        val ms = com.napster.musicapp.repositories.get().milliSecondsToTimer((currentSong.duration)!!.toLong())
        holder.itemView.songName.text = songList.value!![position].title
        holder.itemView.songDur.text = ms
        holder.itemView.songArt.text = songList.value!![position].artist

        holder.itemView.setOnClickListener {
            playerViewModel.setCurrentSong(currentSong)
        }
    }
}

