package com.example.notitiabell.ui.activites

import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.notitiabell.ui.another.PlayAlarm
import com.example.notitiabell.R
import com.example.notitiabell.data.CustomAdapter
import kotlinx.android.synthetic.main.acitivity_nap.*
import java.util.*


class NapActivity:AppCompatActivity(), TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null
    private var buttonSpeak: Button? = null
    private lateinit var ca : CustomAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_nap)

        ca = CustomAdapter(this)

        PlayAlarm.stopAudio()
        tts = TextToSpeech(this,this)
        buttonSpeak = this.closeAlarm_btn
        buttonSpeak!!.isEnabled = false

        buttonSpeak!!.setOnClickListener {

            //Take from database
            var data: String = ca.getAllData()
            data += "Have a wonderful day"

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tts!!.speak(data,TextToSpeech.QUEUE_FLUSH,null,null)

            }else{
                val hash = HashMap<String,String>()
                hash[TextToSpeech.Engine.KEY_PARAM_STREAM] = AudioManager.STREAM_NOTIFICATION.toString()
                tts!!.speak(data,TextToSpeech.QUEUE_FLUSH,hash)
            }
        }

        //Close app
        leave_btn.setOnClickListener{
            finishAffinity()
        }



    }

    override fun onInit(status: Int) {

        if(status ==TextToSpeech.SUCCESS){
            var result = tts!!.setLanguage(Locale.US)
            if(result != TextToSpeech.LANG_MISSING_DATA || result != TextToSpeech.LANG_NOT_SUPPORTED){
                buttonSpeak!!.isEnabled = true
            }

        }
    }

    public override fun onDestroy() {

        super.onDestroy()
    }


}