package com.WarwickWestonWright.MM_Messenger

import android.app.Application
import androidx.room.Room
import com.WarwickWestonWright.MM_Messenger.Data.Room.MsgThreadsDb

class App : Application() {

    lateinit var msgThreadsDb: MsgThreadsDb

    override fun onCreate() {
        super.onCreate()
        instance = this
        msgThreadsDb = Room.databaseBuilder(this, MsgThreadsDb::class.java, "MsgThreadsDb").build()
    }

    companion object {
        @JvmStatic
        private lateinit var instance : App
        fun getApp() : App {
            return instance
        }
    }

}