package com.WarwickWestonWright.MM_Messenger.Utilities.TimeUtils

import com.WarwickWestonWright.MM_Messenger.Constants.ONE_HOUR
import com.WarwickWestonWright.MM_Messenger.Constants.TWENY_SECONDS
import java.text.SimpleDateFormat
import java.util.*

class TimeUtils {

    fun olderThanAnHour(timeStamp : Long?) : Boolean {
        val currentTimeStamp = Date().time
        return if(timeStamp != null) {
            currentTimeStamp - ONE_HOUR > timeStamp
        } else {
            false
        }
    }

    fun olderThan20seconds(timeStamp : Long?) : Boolean {
        val currentTimeStamp = Date().time
        return if(timeStamp != null) {
            currentTimeStamp - TWENY_SECONDS > timeStamp
        } else {
            false
        }
    }

    fun timeStampToLocalTime(timeStamp: Long) : String {
        val date = Date(timeStamp)
        val sdf = SimpleDateFormat("EE HH:MM", Locale.getDefault())
        return sdf.format(date)
    }

}