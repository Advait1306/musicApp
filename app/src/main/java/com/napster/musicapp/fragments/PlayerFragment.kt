package com.napster.musicapp.fragments

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.napster.musicapp.R
import com.napster.musicapp.viewModels.PlayerViewModel
import kotlinx.android.synthetic.main.fragment_player.*

class PlayerFragment : Fragment() {

    private val TAG = "PLAYER FRAGMENT"
    private val playerViewModel: PlayerViewModel by activityViewModels()
    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playerViewModel.getCurrentSongLiveData().observe(viewLifecycleOwner, Observer { song ->
            currentSongName.text = ""
            Log.d(TAG, "Song change detected")
            if (song != null) {
                if (mediaPlayer != null) {
                    mediaPlayer!!.release()
                    mediaPlayer = null
                }

                mediaPlayer = MediaPlayer().apply {
                    Log.d(TAG, song.location.toString())
                    //Change this to setAttributes as this is deprecated
                    setAudioStreamType(AudioManager.STREAM_MUSIC)
                    setDataSource(requireContext(), song.location!!)
                    prepare()
                    start()
                }

                isPlaying = true
                pauseButton.setBackgroundResource(R.drawable.pause)
                currentSongName.text = song.title
            }
        })

        stopButton.setOnClickListener {
            if (mediaPlayer != null) {
                mediaPlayer!!.release()
                mediaPlayer = null
            }
            playerViewModel.setCurrentSong(null)
        }

        pauseButton.setOnClickListener {
            if (mediaPlayer != null) {
                if (isPlaying) {
                    mediaPlayer!!.pause()
                    isPlaying = false
                    pauseButton.setBackgroundResource(R.drawable.play)
                } else {
                    mediaPlayer!!.start()
                    isPlaying = true
                    pauseButton.setBackgroundResource(R.drawable.pause)
                }
            }
        }

        /*mediaPlayer!!.setOnCompletionListener {
           SongRecyclerViewAdapter().nextSong(position + 1)
        }*/
    }
}