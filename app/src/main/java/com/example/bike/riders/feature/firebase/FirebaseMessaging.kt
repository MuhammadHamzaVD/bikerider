package com.example.bike.riders.feature.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.bike.riders.R
import com.example.bike.riders.feature.main.view.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.*

class FirebaseMessaging: FirebaseMessagingService(){
    lateinit var title: String
    lateinit var message: String
    var CHANNEL_ID="CHANNEL"

    lateinit var manager: NotificationManager

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        title = p0.notification!!.title!!
        message = p0.notification!!.body!!


        manager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        sendNotification()
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d(TAG, "Refreshed token: $p0")
    }

    private fun sendNotification(){
        var intent= Intent(applicationContext,MainActivity::class.java)

        intent.putExtra("title", title)
        intent.putExtra("message", message)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            var channel = NotificationChannel(
                CHANNEL_ID,
                "pushnotification",NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }

        var builder=NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.splashbackgourd)
            .setAutoCancel(true)
            .setContentTitle(message)

        var pendingIntent = PendingIntent.getActivity(applicationContext,0,intent,PendingIntent.FLAG_ONE_SHOT)
        builder.setContentIntent(pendingIntent)
        manager.notify(0,builder.build())
        manager
    }

}