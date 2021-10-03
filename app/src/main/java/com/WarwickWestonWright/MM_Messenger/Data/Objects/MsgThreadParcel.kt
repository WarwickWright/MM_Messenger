package com.WarwickWestonWright.MM_Messenger.Data.Objects

import android.os.Parcel
import android.os.Parcelable

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

    constructor(parcel: Parcel) : this() {
        uid = parcel.readInt()
        outId = parcel.readString().toString()
        outLabel = parcel.readString().toString()
        inId = parcel.readString().toString()
        inLabel = parcel.readString().toString()
        isOutgoing = parcel.readByte() != 0.toByte()
        timeStamp = parcel.readLong()
        message = parcel.readString().toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(uid)
        parcel.writeString(outId)
        parcel.writeString(outLabel)
        parcel.writeString(inId)
        parcel.writeString(inLabel)
        parcel.writeByte(if (isOutgoing) 1 else 0)
        parcel.writeLong(timeStamp)
        parcel.writeString(message)
    }

    override fun describeContents(): Int { return 0 }

    companion object CREATOR : Parcelable.Creator<MsgThreadParcel> {
        override fun createFromParcel(parcel: Parcel): MsgThreadParcel { return MsgThreadParcel(parcel) }
        override fun newArray(size: Int): Array<MsgThreadParcel?> { return arrayOfNulls(size) }
    }

}