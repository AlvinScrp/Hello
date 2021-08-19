package com.a.window

import android.app.Activity
import android.app.AlertDialog
import android.content.*
import android.os.Bundle
import android.util.Log
import android.view.Choreographer
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_broadcast.*
import kotlinx.android.synthetic.main.activity_broadcast.btnShowDialog
import kotlinx.android.synthetic.main.activity_main2.*

class WindowActivity : FragmentActivity() ,Choreographer.FrameCallback{


    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)


        btnStartactivity.setOnClickListener {
            startActivity(Intent(this@WindowActivity, Main2Activity::class.java))
        }
        btnShowDialog.setOnClickListener { showDialogTest() }



        btnChoreographer.setOnClickListener { doFrame(System.nanoTime()) }

        btnInvalidate.setOnClickListener {  blink.invalidate()  }

    }
    override fun doFrame(frameTimeNanos: Long) {
        Log.d("alvin", "frameTimeNanos:${frameTimeNanos}")
        Choreographer.getInstance().postFrameCallback(this)
    }

    private fun showDialogTest() {

        try {
            AlertDialog.Builder(applicationContext).setMessage("hahha").show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



}