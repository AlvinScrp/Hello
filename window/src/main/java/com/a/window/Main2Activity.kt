package com.a.window

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btnStartActivity3.setOnClickListener {
            startActivity(Intent(this@Main2Activity,Main3Activity::class.java))
        }

        btnShowDialog.setOnClickListener { showDialogTest() }

//        showDialogTest()
    }

    private fun showDialogTest() {

        AlertDialog.Builder(applicationContext).setMessage("hahha").show();
    }
}