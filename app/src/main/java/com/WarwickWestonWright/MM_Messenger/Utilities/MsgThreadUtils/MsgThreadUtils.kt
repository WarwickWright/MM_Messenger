package com.WarwickWestonWright.MM_Messenger.Utilities.MsgThreadUtils

import com.WarwickWestonWright.MM_Messenger.Data.Objects.MsgThreadParcel

class MsgThreadUtils {
    fun findItemByUid(msgThreadParcel : MsgThreadParcel, msgThreadParcels : MutableList<MsgThreadParcel>) : MsgThreadParcel? {
        for(item in msgThreadParcels) {
            if(item.getUid() == msgThreadParcel.getUid()) {
                return item
            }
        }
        return null
    }
}