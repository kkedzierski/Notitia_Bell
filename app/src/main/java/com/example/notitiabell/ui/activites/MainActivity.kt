package com.example.notitiabell.ui.activites

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.notitiabell.ui.another.Message
import com.example.notitiabell.ui.time.PopTime
import com.example.notitiabell.R
import com.example.notitiabell.ui.time.SaveData
import com.example.notitiabell.data.CustomAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.net.URL
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    //Variable to send the data
    lateinit var name : EditText
    lateinit var date : TextView
    lateinit var weather : TextView
    lateinit var reminder : EditText
    lateinit var ca : CustomAdapter

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.name_tv)
        date = findViewById(R.id.date_tv)
        weather = findViewById(R.id.weather_tv)
        reminder = findViewById(R.id.reminder_et)
        ca = CustomAdapter(this)
        ca.deleteAllRow()

        //region DateTime Show
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val month = Calendar.getInstance().get(Calendar.MONTH)
        val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        val currentDate = LocalDateTime.of(year,month+1,day+1,0,0)
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE
        val formatted = currentDate.format(formatter)


        date.text = formatted.toString()
        //endregion DateTime Show

        //Time display
        val saveData=
            SaveData(applicationContext)
        time_tv.text = saveData.getHours().toString() + ":" + saveData.getMinute().toString()

        //region switches
        time_switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                //Use setAlarm method from saveData class
                saveData.setAlarm()
                Message.message(
                    this,
                    "Alarm created"
                )
            }
        }
        name_switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (name.text.toString().isNotEmpty()) {
                    var id: Long = ca.insertData("name", "Hello " + name.text.toString())
                    if (id < 0)
                        Message.message(
                            this,
                            "name add UnSuccessful"
                        )
                    else
                        Message.message(
                            this,
                            "Successfully inserted name"
                        )
                }
                else {
                    Message.message(
                        applicationContext,
                        "Write your name"
                    )
                    name_switch.isChecked = false

                }
            }
        }
        date_switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                    var id: Long = ca.insertData("date", "Today is " + date.text.toString())
                    if (id < 0)
                        Message.message(
                            this,
                            "date add UnSuccessful"
                        )
                    else
                        Message.message(
                            this,
                            "Successfully inserted date"
                        )
                }

        }

        weather_switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                    var id: Long =
                        ca.insertData("weather", "Today weather is " + weather.text.toString())
                    if (id < 0)
                        Message.message(
                            this,
                            "weather add UnSuccessful"
                        )
                    else
                        Message.message(
                            this,
                            "Successfully inserted weather"
                        )
                }

        }

        reminder_switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (reminder.text.toString().isNotEmpty()) {
                    var id: Long =
                        ca.insertData("reminder", "Remember: " + reminder.text.toString())
                    if (id < 0)
                        Message.message(
                            this,
                            "reminder add UnSuccessful"
                        )
                    else
                        Message.message(
                            this,
                            "Successfully inserted reminder "
                        )
                } else{
                    Message.message(
                        this,
                        "Write reminder"
                    )
                    reminder_switch.isChecked = false
                }
            }
        }


        //endregion

        //Weather
       weatherTask().execute()
    }

    //region Add time

    // On Click function to display the popTime fragment
    fun showSetTimeView(v: View)
    {
        val popTime = PopTime()
        val fm = supportFragmentManager
        popTime.show(fm, "Select time")
    }

    fun setTime(Hours:Int, Minute:Int)
    {
        time_tv.text = "$Hours:$Minute"

        // The operations on time are supported by another class: SaveData.kt
        val saveData=
            SaveData(applicationContext)

        //Send the Variables to constructor saveData
        saveData.saveData(Hours,Minute)

    }
    //endregion

    //region Weather

    // Use this class in MainActivity, because i need access to AppCompactActivity class
    inner class weatherTask() : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String?): String? {
            var response:String?
            try{
                response = URL("https://api.darksky.net/forecast/44c9a4598652daa611a5cf4e9a476035/53.13475,16.68959").readText(
                    Charsets.UTF_8
                )
            }catch (e: Exception){
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)

                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("currently")
                val weather = main.getString("summary")

                var temp = main.getInt("temperature")
                temp = ((temp-32)/1.8).toInt()
                findViewById<TextView>(R.id.weather_tv).text = "$temp °C $weather"

            }

        }
//endregion
    }

