/*
Runs a cron job implemented in MessageItemListFragment to dynamically format list backgrounds
* */
package com.WarwickWestonWright.MM_Messenger.Utilities.MsgFormatterCron

import com.WarwickWestonWright.MM_Messenger.Constants.MSG_FORMAT_INTERVAL

class MsgFormatterCron(val iMsgFormatterCron : IMsgFormatterCron) {

    interface IMsgFormatterCron { fun execForeground(currentTime : Long) }
    private var currentTime = 0L
    private var endTime = 0L

    init {
        currentTime = System.currentTimeMillis()
        endTime = currentTime + MSG_FORMAT_INTERVAL
        runCron()
    }

    private fun runCron() {
        Thread {
            while (System.currentTimeMillis() < endTime) {}
            endTime += MSG_FORMAT_INTERVAL
            iMsgFormatterCron.execForeground(System.currentTimeMillis())
            runCron()
        }.start()
    }

}