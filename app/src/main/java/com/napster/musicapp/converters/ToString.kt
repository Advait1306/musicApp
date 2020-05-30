package com.napster.musicapp.converters


//TODO(Refactoring this entire part is very important, please do it later)

class ToString {

    companion object{
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

}