package com.WarwickWestonWright.MM_Messenger.UI.Fragments

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.WarwickWestonWright.MM_Messenger.Data.Objects.MsgThreadParcel
import com.WarwickWestonWright.MM_Messenger.Data.Room.MsgThread
import com.WarwickWestonWright.MM_Messenger.Data.ViewModels.MsgThreadsViewModel
import com.WarwickWestonWright.MM_Messenger.R
import com.WarwickWestonWright.MM_Messenger.UI.ListFragments.MessageItemListFragment
import com.WarwickWestonWright.MM_Messenger.Utilities.Converters.Converters
import com.WarwickWestonWright.MM_Messenger.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private var mainFragmentBinding: MainFragmentBinding? = null
    private val binding get() = mainFragmentBinding!!
    private lateinit var rootView : View
    private lateinit var msgThreads: MutableList<MsgThreadParcel>
    private val converters = Converters()

    private val breakingBadObjViewModel: MsgThreadsViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainFragmentBinding = MainFragmentBinding.inflate(inflater, container, false)
        rootView = binding.root

        breakingBadObjViewModel.selected.observe(viewLifecycleOwner, Observer<MutableList<MsgThread>> { msgThreadObjs ->
            //Update the UI
            updateMasterList(msgThreadObjs)
        })
        return rootView
    }

    override fun onDestroy() {
        super.onDestroy()
        mainFragmentBinding = null
    }

    private fun updateMasterList(msgThreadObjs: MutableList<MsgThread>) {
        msgThreads = converters.mutableListToParcelableArrayList(msgThreadObjs)
        val messageItemListFragment = MessageItemListFragment()
        val bundle = Bundle()
        bundle.putParcelableArrayList("msgThreadObjs", msgThreads as ArrayList<out Parcelable>)
        messageItemListFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.lytMessageListContainer, messageItemListFragment,"MessageItemListFragment")?.commit()
    }

}