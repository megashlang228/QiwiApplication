package com.example.qiwiapplication.notification

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.qiwiapplication.utils.SharedPref
import com.google.firebase.messaging.FirebaseMessagingService

class PushService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        SharedPref.saveTokenPhone(token)
        super.onNewToken(token)
    }

}