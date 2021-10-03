package com.WarwickWestonWright.MM_Messenger.UI.ListFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.WarwickWestonWright.MM_Messenger.Data.Objects.MsgThreadParcel
import com.WarwickWestonWright.MM_Messenger.Data.Room.MsgThread
import com.WarwickWestonWright.MM_Messenger.R
import com.WarwickWestonWright.MM_Messenger.UI.ListFragments.placeholder.PlaceholderContent

class MessageItemListFragment : Fragment() {

    private var msgThreads: MutableList<MsgThreadParcel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let { bundle ->
            //bundle.putParcelableArrayList("msgThreadObjs", msgThreadObjs as ArrayList<out Parcelable>)
            msgThreads = bundle?.getParcelableArrayList("msgThreadObjs")!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.message_item_list_fragment, container, false)
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = MessageItemAdapter(msgThreads)
            }
        }
        return view
    }

}