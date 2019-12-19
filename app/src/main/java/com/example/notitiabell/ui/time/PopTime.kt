package com.example.notitiabell.ui.time

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.example.notitiabell.R
import com.example.notitiabell.ui.activites.MainActivity

class PopTime:DialogFragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Add view from pop_time layout to variable popTimeView
        var popTimeView = inflater.inflate(R.layout.pop_time,container,false)

        // Add item from pop_time layout to variables
        var done_btn = popTimeView.findViewById<Button>(R.id.done_btn) as Button
        var timePicker = popTimeView.findViewById(R.id.timePicker) as TimePicker

        // change to 24 hours set
        timePicker.setIs24HourView(true)

        //Send choose from pop Time to MainActivity method calls setTime
        done_btn.setOnClickListener{
            val ma = activity as MainActivity

            // Different pass to another version of Android on phones
                if(Build.VERSION.SDK_INT>=23)
                    ma.setTime(timePicker.hour,timePicker.minute)
                else
                    ma.setTime(timePicker.currentHour,timePicker.currentMinute)

            this.dismiss()
        }

        return popTimeView
    }
}