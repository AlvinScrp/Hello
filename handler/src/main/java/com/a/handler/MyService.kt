package com.a.handler

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {

    var binder: IBinder? = null
    override fun onCreate() {
        super.onCreate()

//        binder= MyA
    }

    override fun onBind(intent: Intent): IBinder? {
        return binder
    }
}