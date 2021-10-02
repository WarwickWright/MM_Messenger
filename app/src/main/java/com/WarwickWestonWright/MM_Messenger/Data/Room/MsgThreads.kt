package com.WarwickWestonWright.MM_Messenger.Data.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MsgThread(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    @ColumnInfo(name = "out_id") val outId: String?,
    @ColumnInfo(name = "out_label") val outLabel: String?,
    @ColumnInfo(name = "in_id") val inId: String?,
    @ColumnInfo(name = "in_label") val inLabel: String?,
    @ColumnInfo(name = "is_outgoing") val isOutgoing: String?,
    @ColumnInfo(name = "time_stamp") val timeStamp: String?,
    @ColumnInfo(name = "message") val message: String?
)