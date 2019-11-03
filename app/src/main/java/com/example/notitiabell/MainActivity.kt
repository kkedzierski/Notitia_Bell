package com.example.notitiabell

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun showSetTimeView(v:View)
    {
        val popTime = PopTime()
        val fm = supportFragmentManager
        popTime.show(fm, "Select time")
    }

    fun setTime(Hours:Int, Minute:Int)
    {
        time_tv.text = Hours.toString() + ":" + Minute.toString()

    }

    override fun onResume() {
        super.onResume()

    }


}
