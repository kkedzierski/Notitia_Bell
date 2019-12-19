package com.example.notitiabell.ui.another

import android.content.Context
import android.widget.Toast

class Message {

    companion object{

        fun message(con:Context, mes:String){
            Toast.makeText(con,mes,Toast.LENGTH_LONG).show()
        }
    }
}