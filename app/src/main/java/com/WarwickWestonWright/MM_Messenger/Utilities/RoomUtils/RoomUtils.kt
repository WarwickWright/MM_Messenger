package com.WarwickWestonWright.MM_Messenger.Utilities.RoomUtils

import com.WarwickWestonWright.MM_Messenger.Data.Room.MsgThread
import com.WarwickWestonWright.MM_Messenger.Data.Room.MsgThreadsDb

class RoomUtils {

    private var msgThreadsDb : MsgThreadsDb
    private var iRoomUtils : IRoomUtils

    interface IRoomUtils {
        fun deleteAll(msgThreadsDeleted : List<MsgThread>)
        fun insertAll(msgThreadsInserted : List<MsgThread>)
        fun insertMsg(idOfInsertedMsg : Int)
    }

    constructor(iRoomUtils : IRoomUtils, msgThreadsDb: MsgThreadsDb) {
        this.iRoomUtils = iRoomUtils
        this. msgThreadsDb = msgThreadsDb
    }

    fun deleteAll(msgThreads : List<MsgThread>) {
        Thread {
            msgThreadsDb.userDao().deleteAll(msgThreads)
            iRoomUtils.deleteAll(listOf())
        }.start()
    }

    fun insertAll(msgThreads : List<MsgThread>) {
        Thread {
            msgThreadsDb.userDao().insertAll(msgThreads)
            iRoomUtils.insertAll(msgThreads)
        }.start()
    }

    fun insertMsg(msgThread : MsgThread) {
        val idOfInsertedMsg = msgThreadsDb.userDao().insertMsg(msgThread) as Int
        iRoomUtils.insertMsg(idOfInsertedMsg)
    }

}