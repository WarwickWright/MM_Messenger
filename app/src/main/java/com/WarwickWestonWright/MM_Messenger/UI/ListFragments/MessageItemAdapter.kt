package com.WarwickWestonWright.MM_Messenger.UI.ListFragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.WarwickWestonWright.MM_Messenger.Data.Objects.MsgThreadParcel
import com.WarwickWestonWright.MM_Messenger.Utilities.TimeUtils.TimeUtils
import com.WarwickWestonWright.MM_Messenger.databinding.MessageItemFragmentBinding

class MessageItemAdapter(private val values: MutableList<MsgThreadParcel>) : RecyclerView.Adapter<MessageItemAdapter.ViewHolder>() {

    val timeUtils = TimeUtils()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MessageItemFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.itemView.tag = item
        if(item.getIsOutgoing()) {
            holder.lytIncoming.visibility = View.GONE
            holder.lytOutgoing.visibility = View.VISIBLE
            holder.lblOutgoing.text = item.getMessage()
            holder.lblIncoming.text = ""
            holder.lblTimeStampOutgoing.text = timeUtils.timeStampToLocalTime(item.getTimeStamp())
        }
        else {
            holder.lytIncoming.visibility = View.VISIBLE
            holder.lytOutgoing.visibility = View.GONE
            holder.lblOutgoing.text = ""
            holder.lblIncoming.text = item.getMessage()
            holder.lblTimeStampIncoming.text = timeUtils.timeStampToLocalTime(item.getTimeStamp())
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: MessageItemFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val lytOutgoing: LinearLayout = binding.lytOutgoing
        val lytIncoming: LinearLayout = binding.lytIncoming
        val lblOutgoing: TextView = binding.lblOutgoing
        val lblIncoming: TextView = binding.lblIncoming
        val lblTimeStampIncoming: TextView = binding.lblTimeStampIncoming
        val lblTimeStampOutgoing: TextView = binding.lblTimeStampOutgoing

        override fun toString(): String {
            return super.toString()
        }
    }

}