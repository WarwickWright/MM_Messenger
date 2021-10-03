package com.WarwickWestonWright.MM_Messenger.Utilities.TimeUtils

import com.WarwickWestonWright.MM_Messenger.Constants.ONE_HOUR
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
}