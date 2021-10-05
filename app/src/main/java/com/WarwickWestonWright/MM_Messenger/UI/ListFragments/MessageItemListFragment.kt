package com.WarwickWestonWright.MM_Messenger.UI.ListFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.WarwickWestonWright.MM_Messenger.Data.Objects.MsgThreadParcel
import com.WarwickWestonWright.MM_Messenger.R
import com.WarwickWestonWright.MM_Messenger.Utilities.MsgFormatterCron.MsgFormatterCron
import com.WarwickWestonWright.MM_Messenger.Utilities.TimeUtils.TimeUtils

class MessageItemListFragment : Fragment(),
    RecyclerView.OnChildAttachStateChangeListener,
    MsgFormatterCron.IMsgFormatterCron {

    private var msgThreads: MutableList<MsgThreadParcel> = mutableListOf()
    private lateinit var rootView : RecyclerView
    private lateinit var msgFormatterCron : MsgFormatterCron
    private var lastItemIdx = 0
    private val timeUtils = TimeUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        msgFormatterCron = MsgFormatterCron(this)
        arguments.let { bundle ->
            msgThreads = bundle?.getParcelableArrayList("msgThreadObjs")!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.message_item_list_fragment, container, false) as RecyclerView
        if (rootView is RecyclerView) {
            with(rootView) {
                layoutManager = LinearLayoutManager(context)
                adapter = MessageItemAdapter(msgThreads)
            }
            rootView.post {
                rootView.scrollToPosition(msgThreads.size - 1)
            }
        }

        lastItemIdx = msgThreads.size - 1
        rootView.addOnChildAttachStateChangeListener(this)

        return rootView
    }

    override fun onChildViewAttachedToWindow(view: View) {
        val msgThreadParcel = view.tag as MsgThreadParcel
        //Find last Uid
        val lastUid = msgThreads.get(lastItemIdx).getUid()
        //Check if in scope view is the last list item
        //Greater than 20 seconds section
        if(msgThreadParcel.getUid() == lastUid) {
            if(msgThreadParcel.isOutgoing) {
                val lblOutgoing = ((((view as LinearLayout).getChildAt(0) as LinearLayout).
                getChildAt(0) as LinearLayout).getChildAt(0) as LinearLayout).getChildAt(0) as TextView
                setOutgoingTail(lblOutgoing)
            }
        }
        else {
            val lblOutgoing = ((((view as LinearLayout).getChildAt(0) as LinearLayout).
            getChildAt(0) as LinearLayout).getChildAt(0) as LinearLayout).getChildAt(0) as TextView
            if(timeUtils.olderThan20seconds(msgThreadParcel.getTimeStamp())) {
                setOutgoingTail(lblOutgoing)
            }
            else {
                lblOutgoing.background = resources.getDrawable(R.drawable.mm_outgoing_bg, activity?.theme)
            }
        }
        olderThanHourAction(view as LinearLayout)
    }

    private fun setOutgoingTail(lytOutGoingItem : View) {
        lytOutGoingItem.background = resources.getDrawable(R.drawable.mm_outgoing_bg_tail, activity?.theme)
    }

    private fun olderThanHourAction(view : LinearLayout) {
        val msgThreadParcel = view.tag as MsgThreadParcel
        val tsViewOutgoing = (((view.getChildAt(0) as LinearLayout).getChildAt(0) as LinearLayout).
        getChildAt(0) as LinearLayout).getChildAt(1) as TextView//Timestamp View
        val tsViewIncoming = (((view.getChildAt(1) as LinearLayout).getChildAt(1) as LinearLayout).
        getChildAt(0) as LinearLayout).getChildAt(1) as TextView//Timestamp View
        if(msgThreads.size > 1) {
            if(msgThreadParcel.isOutgoing) {
                if(timeUtils.olderThanAnHour(msgThreadParcel.getTimeStamp())) {
                    tsViewOutgoing.visibility = View.VISIBLE
                }
                else {
                    tsViewOutgoing.visibility = View.GONE
                }
                if(timeUtils.olderThan20seconds(msgThreadParcel.getTimeStamp())) {
                    val lblOutgoing = (((view.getChildAt(1) as LinearLayout).getChildAt(1) as LinearLayout).
                    getChildAt(0) as LinearLayout).getChildAt(0) as TextView//Message View
                    setOutgoingTail(lblOutgoing)
                }
            }
            else {
                if(timeUtils.olderThanAnHour(msgThreadParcel.getTimeStamp())) {
                    tsViewIncoming.visibility = View.VISIBLE
                }
                else {
                    tsViewIncoming.visibility = View.GONE
                }
            }
        }
        else if(msgThreads.size == 1) {
            if(msgThreadParcel.isOutgoing) {
                tsViewOutgoing.visibility = View.VISIBLE
            }
            else {
                tsViewIncoming.visibility = View.VISIBLE
            }
        }
    }

    override fun onChildViewDetachedFromWindow(view: View) {}

    override fun execForeground(currentTime: Long) {
        activity?.runOnUiThread {
            for(i in 0 until msgThreads.size) {
                val view = rootView.findViewWithTag<LinearLayout>(msgThreads[i])
                if(view != null) {
                    val msgThreadParcel = view.tag as MsgThreadParcel
                    olderThanHourAction(view)
                    if(timeUtils.olderThan20seconds(msgThreadParcel.getTimeStamp())) {
                        val lblOutgoing = (((view.getChildAt(0) as LinearLayout).getChildAt(0) as LinearLayout).
                        getChildAt(0) as LinearLayout).getChildAt(0) as TextView
                        setOutgoingTail(lblOutgoing)
                    }
                }
            }
        }
    }

}