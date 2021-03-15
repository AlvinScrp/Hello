package com.a.binderclient

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.a.binder.IMyService
import kotlinx.android.synthetic.main.activity_binder_client.*
import java.lang.Exception

class BinderClientActivity : AppCompatActivity(), ServiceConnection {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binder_client)
        getService.setOnClickListener { handleService() }
        sayHello.setOnClickListener { sayHello() }
        addCallback.setOnClickListener { addCallback() }
    }

    private fun handleService() {
        var intent = Intent()
        intent.component = ComponentName(
                "com.a.binderserver",
                "com.a.binderserver.BinderServerService")
//        startService(intent);
        bindService(intent, this, BIND_AUTO_CREATE)
    }

    var myService: MyServiceProxy? = null

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        Log.d("alvin", "onServiceConnected: $service")
        myService = MyServiceProxy(service);

    }
    override fun onServiceDisconnected(name: ComponentName?) {

    }
    private fun addCallback(){
        var contentResolver =contentResolver
        myService?.addCallback(CallbackStub())
    }

    private fun sayHello() {


        myService?.sayHello("ahaahah")
    }

}