package com.example.notitiabell.ui.time

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.notitiabell.ui.another.PlayAlarm

class myBroadcastReceiver: BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {

        if(intent!!.action.equals("com.tester.alarmmanager"))
        {
            if (context != null) {
                PlayAlarm.playAudio(context)
            }
            val notfyMe = Notifications()
            notfyMe.notify(context!!)

        }
        else if(intent!!.action == "android.intent.action.BOOT_COMPLETED")
        {
            val saveData = SaveData(context!!)
            saveData.setAlarm()

        }
    }




}