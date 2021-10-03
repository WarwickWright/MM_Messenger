package com.WarwickWestonWright.MM_Messenger.UI.ListFragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.WarwickWestonWright.MM_Messenger.Data.Objects.MsgThreadParcel
import com.WarwickWestonWright.MM_Messenger.databinding.MessageItemFragmentBinding

class MessageItemAdapter(private val values: MutableList<MsgThreadParcel>) : RecyclerView.Adapter<MessageItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MessageItemFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        if(item.getIsOutgoing()) {
            holder.lytInComing.visibility = View.GONE
            holder.lytOutGoing.visibility = View.VISIBLE
            holder.lblOutGoing.text = item.getMessage()
            holder.lblInComing.text = ""
            holder.lblTimeStampOutgoing.text = item.getTimeStamp().toString()
        }
        else {
            holder.lytInComing.visibility = View.VISIBLE
            holder.lytOutGoing.visibility = View.GONE
            holder.lblOutGoing.text = ""
            holder.lblInComing.text = item.getMessage()
            holder.lblTimeStampIncoming.text = item.getTimeStamp().toString()
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: MessageItemFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val lytOutGoing: LinearLayout = binding.lytOutGoing
        val lytInComing: LinearLayout = binding.lytInComing
        val lblOutGoing: TextView = binding.lblOutGoing
        val lblInComing: TextView = binding.lblInComing
        val lblTimeStampIncoming: TextView = binding.lblTimeStampIncoming
        val lblTimeStampOutgoing: TextView = binding.lblTimeStampOutgoing

        override fun toString(): String {
            return super.toString()
        }
    }

}