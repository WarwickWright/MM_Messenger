package com.WarwickWestonWright.MM_Messenger.Data.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MsgThreadDao {
    @Query("SELECT * FROM MsgThread")
    fun getAll(): List<MsgThread>

    @Query("SELECT * FROM MsgThread WHERE in_id IN (:inId)")
    fun loadAllByIds(inId: String): List<MsgThread>//Open ended so that can filter by incoming messages

    @Insert
    fun insertMsg(msgThread : MsgThread) : Long

    @Insert
    fun insertAll(vararg users: MsgThread)//Inserts all fields for a record

    @Insert
    fun insertAll(msgThreads : List<MsgThread>)//Inserts all fields for a record

    @Delete
    fun delete(ouId: MsgThread)//Delete entity

    @Delete
    fun deleteAll(msgThreads : List<MsgThread>)//Erases list of entities.
}