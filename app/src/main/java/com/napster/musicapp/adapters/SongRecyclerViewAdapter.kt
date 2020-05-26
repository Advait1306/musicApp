package com.napster.musicapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.napster.musicapp.R
import com.napster.musicapp.repositories.Song
import kotlinx.android.synthetic.main.song_list_view.view.*

class SongRecyclerViewAdapter(val songList: ArrayList<Song>): RecyclerView.Adapter<SongRecyclerViewAdapter.ViewHolder>(){
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.song_list_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.songName.text = songList[position].title
    }
}