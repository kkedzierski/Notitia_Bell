package com.example.notitiabell.ui.time

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.widget.RemoteViews
import com.example.notitiabell.R
import com.example.notitiabell.ui.activites.NapActivity

class Notifications{

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "com.example.notitiabell"
    private val description = "Wake up!"
    fun notify(context: Context){
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val intent = Intent(context, NapActivity::class.java)
            val pi = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

            val contentView = RemoteViews(context.packageName,
                R.layout.notification_layout
            )
            contentView.setTextViewText(R.id.title_tv, "Wake up! - Alarm")
            contentView.setTextViewText(R.id.content_tv, "Click to mute!")

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(true)
                notificationManager.createNotificationChannel(notificationChannel)
                builder = Notification.Builder(context,channelId)
                    .setContent(contentView)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(context.resources,
                        R.drawable.ic_launcher
                    ))
                    .setContentIntent(pi)
                    .setAutoCancel(true)

            }
            else{
                builder = Notification.Builder(context,channelId)
                    .setContent(contentView)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setLargeIcon(BitmapFactory.decodeResource(context.resources,
                        R.drawable.ic_launcher
                    ))
                    .setContentIntent(pi)
                    .setAutoCancel(true)
            }
            notificationManager.notify(1234,builder.build())

        }
    }
