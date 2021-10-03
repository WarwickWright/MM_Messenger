package com.WarwickWestonWright.MM_Messenger

import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.WarwickWestonWright.MM_Messenger.Constants.ONE_HOUR
import com.WarwickWestonWright.MM_Messenger.Data.Room.MsgThread
import com.WarwickWestonWright.MM_Messenger.Data.Room.MsgThreadsDb
import com.WarwickWestonWright.MM_Messenger.FileHandlers.TextAssetMan
import com.WarwickWestonWright.MM_Messenger.Utilities.RoomUtils.RoomUtils
import com.WarwickWestonWright.MM_Messenger.Utilities.TimeUtils.TimeUtils
import com.WarwickWestonWright.MM_Messenger.databinding.MainActivityBinding
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity(), TextAssetMan.ITextAssetMan, RoomUtils.IRoomUtils {

    private lateinit var binding: MainActivityBinding
    private lateinit var rootView : View
    private lateinit var app: App
    private lateinit var msgThreadsDb: MsgThreadsDb
    private var msgThreads: MutableList<MsgThread> = mutableListOf()
    private lateinit var am : AssetManager
    private lateinit var textAssetMan: TextAssetMan
    private lateinit var roomUtils: RoomUtils
    private lateinit var timeUtils: TimeUtils

    //private lateinit var btnToggleInOutGoing : Button
    private lateinit var bntClearThread : Button
    private lateinit var txtMsg : EditText
    private lateinit var btnSendMsg : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        rootView = binding.root
        setContentView(rootView)
        //setContentView(R.layout.main_activity)
        app = App.getApp()
        am = assets
        textAssetMan = TextAssetMan(this, app.assets)
        textAssetMan.getTextAsset("mm_test_thread.json")
        msgThreadsDb = app.msgThreadsDb
        roomUtils = RoomUtils(this, msgThreadsDb)
        timeUtils = TimeUtils()
        binding.btnToggleInOutGoing.setOnClickListener {
            Toast.makeText(this, "ToDo", Toast.LENGTH_SHORT).show()
        }

        binding.bntClearThread.setOnClickListener {
            Toast.makeText(this, "ToDo", Toast.LENGTH_SHORT).show()
        }

        binding.btnSendMsg.setOnClickListener {
            Toast.makeText(this, "ToDo: " + binding.txtMsg.text.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun getText(text: String) {
        var msgThreadJSon : JSONArray = JSONObject(text).getJSONArray("muzmatch_message_history")
        for (i in 0 until msgThreadJSon.length()) {
            val jso = msgThreadJSon.get(i) as JSONObject
            val outId = jso.getString("out_id")
            val outLabel = jso.getString("out_label")
            val inId = jso.getString("in_id")
            val inLabel = jso.getString("in_label")
            val isOutgoing = jso.getString("is_outgoing").toBoolean()
            val timeStamp = jso.getString("time_stamp").toLong()
            val message = jso.getString("message")
            val msgThread = MsgThread(uid = 0,
                outId = outId as String?,
                outLabel = outLabel as String?,
                inId = inId as String?,
                inLabel = inLabel as String?,
                isOutgoing = isOutgoing as Boolean?,
                timeStamp = timeStamp as Long?,
                message = message as String?
            )
            /* Test Code For Older than hour */
            if(msgThread.timeStamp!! == 0L) {
                msgThread.timeStamp = Date().time - (ONE_HOUR - 1000)
            }
            /* Test Code For Older than hour */
            msgThreads.add(msgThread)
            timeUtils.olderThanAnHour(msgThread.timeStamp)
            val x = 0
        }
        //msgThreadsDb.userDao().insertAll(msgThreads)//Use this if you don't want list to be updated
        roomUtils.insertAll(msgThreads)//Update db through roomUtils to update list
    }

    override fun onStop() {
        super.onStop()
        Thread {
            msgThreads = msgThreadsDb.userDao().getAll().toMutableList()
            //msgThreadsDb.userDao().deleteAll(msgThreads)//Use this if you don't want list to be updated
            roomUtils.deleteAll(msgThreads)//Update db through roomUtils to update list
        }.start()
    }

    override fun deleteAll(msgThreadsDeleted: List<MsgThread>) {
        runOnUiThread {
            Toast.makeText(this, msgThreads.size.toString() + " Records Deleted", Toast.LENGTH_LONG).show()
        }
    }

    override fun insertAll(msgThreadsInserted: List<MsgThread>) {
        runOnUiThread {
            Toast.makeText(this, msgThreads.size.toString() + " Records Created", Toast.LENGTH_LONG).show()
        }
    }

    override fun insertMsg(idOfInsertedMsg: Int) {
        runOnUiThread {
            msgThreads = msgThreadsDb.userDao().getAll().toMutableList()
            Toast.makeText(this, msgThreads.size.toString() + " Records Created", Toast.LENGTH_LONG).show()
        }
    }

}