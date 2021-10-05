package com.WarwickWestonWright.MM_Messenger.Utilities.TimeUtils

import com.WarwickWestonWright.MM_Messenger.Constants.ONE_HOUR
import com.WarwickWestonWright.MM_Messenger.Constants.TWENY_SECONDS
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

}