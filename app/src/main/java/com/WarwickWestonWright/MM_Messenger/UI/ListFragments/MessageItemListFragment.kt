package com.WarwickWestonWright.MM_Messenger.UI.ListFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.WarwickWestonWright.MM_Messenger.Data.Objects.MsgThreadParcel
import com.WarwickWestonWright.MM_Messenger.R
import com.WarwickWestonWright.MM_Messenger.Utilities.MsgFormatterCron.MsgFormatterCron
import com.WarwickWestonWright.MM_Messenger.Utilities.MsgThreadUtils.MsgThreadUtils

class MessageItemListFragment : Fragment(),
    RecyclerView.OnChildAttachStateChangeListener,
    MsgFormatterCron.IMsgFormatterCron {

    private var msgThreads: MutableList<MsgThreadParcel> = mutableListOf()
    private lateinit var rootView : RecyclerView
    private lateinit var msgFormatterCron : MsgFormatterCron
    private var lastItemIdx = 0
    private val msgThreadUtils = MsgThreadUtils()

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
        if(msgThreadParcel.getUid() == lastUid) {
            Toast.makeText(activity, "Last Index: $lastItemIdx", Toast.LENGTH_SHORT).show()

        }
        else {
            Toast.makeText(activity, "TimeStamp: " + msgThreadParcel.getTimeStamp(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onChildViewDetachedFromWindow(view: View) {
        //
    }

    override fun execForeground(currentTime: Long) {}

}