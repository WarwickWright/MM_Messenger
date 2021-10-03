package com.WarwickWestonWright.MM_Messenger.UI.Fragments

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.WarwickWestonWright.MM_Messenger.Data.Room.MsgThread
import com.WarwickWestonWright.MM_Messenger.Data.ViewModels.MsgThreadsViewModel
import com.WarwickWestonWright.MM_Messenger.R
import com.WarwickWestonWright.MM_Messenger.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private var mainFragmentBinding: MainFragmentBinding? = null
    private val binding get() = mainFragmentBinding!!
    private lateinit var rootView : View
    private lateinit var msgThreadObjs : MutableList<MsgThread>

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
        Toast.makeText(activity, "Updating UI: " + msgThreadObjs.size.toString(), Toast.LENGTH_SHORT).show()
    }

}