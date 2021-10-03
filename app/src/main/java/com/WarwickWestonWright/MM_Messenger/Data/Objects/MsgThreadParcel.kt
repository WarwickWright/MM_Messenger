package com.WarwickWestonWright.MM_Messenger.Data.Objects

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MsgThreadParcel() : Parcelable {
    private var uid : Int = 0
    private var outId : String = ""
    private var outLabel : String = ""
    private var inId : String = ""
    private var inLabel : String = ""
    var isOutgoing : Boolean = true
    private var timeStamp : Long = 0L
    private var message : String = ""

    constructor(
        uid : Int,
        outId : String,
        outLabel : String,
        inId : String,
        inLabel : String,
        isOutgoing : Boolean,
        timeStamp : Long,
        message : String
    ) : this() {
        this.uid = uid
        this.outId = outId
        this.outLabel = outLabel
        this.inId = inId
        this.inLabel = inLabel
        this.isOutgoing = isOutgoing
        this.timeStamp = timeStamp
        this.message = message
    }

    //Accessors
    fun getUid() : Int { return this.uid }
    fun getOutId() : String { return this.outId }
    fun getOutLabel() : String { return this.outLabel }
    fun getInId() : String { return this.inId }
    fun getInLabel() : String { return this.inLabel }
    fun getIsOutgoing() : Boolean { return this.isOutgoing }
    fun getTimeStamp() : Long { return this.timeStamp }
    fun getMessage() : String { return this.message }

    //Mutators
    fun setUid(uid : Int) { this.uid = uid }
    fun sgetOutId(outId : String) { this.outId = outId }
    fun setOutLabel(outLabel : String) { this.outLabel = outLabel }
    fun setInId(inId : String) { this.inId = inId }
    fun setInLabel(inLabel : String) { this.inLabel = outLabel }
    fun setIsOutgoing(isOutgoing : Boolean) { this.isOutgoing = isOutgoing }
    fun setTimeStamp(timeStamp : Long) { this.timeStamp = timeStamp }
    fun setMessage(message : String) { this.message = message }

}