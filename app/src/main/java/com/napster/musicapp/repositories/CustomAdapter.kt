package com.napster.musicapp.repositories
/*
import android.content.Context
import android.database.DataSetObserver
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import com.napster.musicapp.MainActivity
import com.napster.musicapp.R
import com.napster.musicapp.*

class CustomAdapter(mainActivity: MainActivity, audioList: Any?) : ListAdapter {

    private lateinit var typeface:Typeface
    fun Customadapter(context :Context, custom_album_child:Int, albumarr:ArrayList<Any>) {
        Customadapter(context, custom_album_child, albumarr)
        typeface = Typeface.createFromAsset(context.assets,"font.ttf")
    }

    override fun isEmpty(): Boolean {
        return false
    }
    override fun getItem(position: Int) {
        return getItem(position)
    }

    override fun getViewTypeCount(): Int {
        TODO("Not yet implemented")
    }

    override fun isEnabled(position: Int): Boolean {
        return true
    }

    override fun getItemId(position: Int): Long {
        TODO()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun areAllItemsEnabled(): Boolean {
        return true
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    @NonNull
    override fun getView(position:Int, convertView:View, @NonNull parent:ViewGroup) : View {
        val view = convertView
        val layoutInflater:LayoutInflater = LayoutInflater.from(view.context)
        var songfileinfo :Songfileinfo = getItem(position) as Songfileinfo


        if (songfileinfo != null) {
            textView.text = songfileinfo.title
            textView.setTypeface(typeface)
        }
        if (songfileinfo != null) {
            textView1.text = songfileinfo.artist
            textView1.setTypeface(typeface)
        }
        val timer :Long?

        if (songfileinfo != null) {
            timer = songfileinfo.duration?.toLong()
            val ms = timer?.let { milliSecondsToTimer(it) }
            textView2.text = ms.toString()
            textView2.setTypeface(typeface)
        }

        return view
    }

    override fun registerDataSetObserver(observer: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun getItemViewType(position: Int): Int {
        TODO("Not yet implemented")
    }


    private fun milliSecondsToTimer(milliseconds:Long):String {
        lateinit var finalTimerString :String
        lateinit var secondsString :String

// Convert total duration into time
        val hours = (milliseconds / (1000 * 60 * 60)).toInt()
        val minutes = ((milliseconds % (1000 * 60 * 60)) / (1000 * 60)).toInt()
        val seconds = ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000).toInt()
// Add hours if there
        if (hours > 0) {
            finalTimerString = "$hours :"
        }

// Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0$seconds"
        } else {
            secondsString = "$seconds"
        }

        finalTimerString = "$finalTimerString $minutes : $secondsString"

// return timer string
        return finalTimerString
    }

}*/