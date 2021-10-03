package com.WarwickWestonWright.MM_Messenger.Utilities.Converters

import com.WarwickWestonWright.MM_Messenger.Data.Objects.MsgThreadParcel
import com.WarwickWestonWright.MM_Messenger.Data.Room.MsgThread

class Converters {
    fun mutableListToParcelableArrayList(msgThreads: MutableList<MsgThread>) : MutableList<MsgThreadParcel> {
        val returnVal = mutableListOf<MsgThreadParcel>()
        for(msgThread in msgThreads) {
            val msgThreadParcel = MsgThreadParcel(
                msgThread.uid,
                msgThread.outId!!,
                msgThread.outLabel!!,
                msgThread.inId!!,
                msgThread.inLabel!!,
                msgThread.isOutgoing!!,
                msgThread.timeStamp!!,
                msgThread.message!!
            )
            returnVal.add(msgThreadParcel)
        }
        return returnVal
    }
}