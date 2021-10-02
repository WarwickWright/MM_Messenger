package com.WarwickWestonWright.MM_Messenger.Data.Room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(MsgThread::class), version = 1)
abstract class MsgThreadsDb : RoomDatabase() {
    abstract fun userDao(): MsgThreadDao
}