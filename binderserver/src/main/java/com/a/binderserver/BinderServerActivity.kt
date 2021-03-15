package com.a.binderserver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_binder_server.*

class BinderServerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binder_server)

        addService.setOnClickListener {
            startService(Intent(this@BinderServerActivity,BinderServerService::class.java))
//            BinderUtil.addService(ServiceName, MyService())
        }
    }

}


