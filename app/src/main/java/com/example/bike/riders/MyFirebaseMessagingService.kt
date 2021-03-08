package com.example.bike.riders

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService(){
    val TAG = "FCM Service"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG,"From: "+remoteMessage!!.from)
        Log.d(TAG,"Notification Message Body: "+remoteMessage.notification!!.body!!)
    }
}