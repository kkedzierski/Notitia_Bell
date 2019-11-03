package com.example.notitiabell

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment

class PopTime:DialogFragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var popTimeView = inflater.inflate(R.layout.pop_time,container,false)
        var done_btn = popTimeView.findViewById<Button>(R.id.done_btn) as Button
        var timePicker = popTimeView.findViewById(R.id.timePicker) as TimePicker

        done_btn.setOnClickListener{
            val ma = activity as MainActivity
                if(Build.VERSION.SDK_INT>=23)
                    ma.setTime(timePicker.hour,timePicker.minute)
                else
                    ma.setTime(timePicker.currentHour,timePicker.currentMinute)



            this.dismiss()
        }

        return popTimeView
    }
}