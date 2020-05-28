package com.napster.musicapp.repositories

class MillitoString{
    fun milliSecondsToTimer(milliseconds:Long):String {

        val hours = (milliseconds / (1000 * 60 * 60)).toInt()
        val minutes = ((milliseconds % (1000 * 60 * 60)) / (1000 * 60)).toInt()
        val seconds = ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000).toInt()
        lateinit var sec: String
        lateinit var min: String
        lateinit var hour: String
        lateinit var finalTimerString :String
        sec = if (seconds > 10) {
            "$seconds"
        } else {
            "0$seconds"
        }
        min = if (minutes > 10) {
            "$minutes"
        } else {
            "0$minutes"
        }
        hour = if (hours > 10) {
            "$hours"
        } else {
            "0$hours"
        }
        if (hours > 0) {
            finalTimerString = hour + " : " + min + " : " + sec
        } else {
            finalTimerString = min + " : " + sec
        }
// return timer string
        return finalTimerString
    }

}