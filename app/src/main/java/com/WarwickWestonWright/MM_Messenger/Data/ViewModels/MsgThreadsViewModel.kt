package com.WarwickWestonWright.MM_Messenger.Data.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.WarwickWestonWright.MM_Messenger.Data.Room.MsgThread

class MsgThreadsViewModel : ViewModel() {
    val selected = MutableLiveData<MutableList<MsgThread>>()
    fun select(MsgThreadObjs: MutableList<MsgThread>) {
        selected.value = MsgThreadObjs
    }
}