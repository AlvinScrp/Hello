package com.a.handler

import android.os.*
import android.os.MessageQueue.OnFileDescriptorEventListener.EVENT_INPUT
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_handler_main.*
import java.io.FileNotFoundException
import java.io.IOException

class HandlerMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_main)

        var sds = ParcelFileDescriptor.createPipe()
        var pfdRead = sds[0]
        var pfdWrite = sds[1]

        Looper.getMainLooper().queue
                .addOnFileDescriptorEventListener(pfdRead.fileDescriptor, EVENT_INPUT)
                { _, events -> tv.text = readPipe(pfdRead); events }

        btn.setOnClickListener { writePipe("hello", pfdWrite) }


        //构建一个处理message的handler
        val handler = object : Handler() {
            override fun dispatchMessage(msg: Message) {
                Log.d("alvin", "msg.what:${msg.what}")
            }
        }
        //发一个消息
        handler.sendMessageDelayed(Message().also { it.what = 1 },1000L)

        //post 方式发送消息
        Handler().post { Log.d("alvin", "do post body") }
    }

    private fun writePipe(message: String, pfdWrite: ParcelFileDescriptor) {
        try {
            val output = ParcelFileDescriptor.AutoCloseOutputStream(pfdWrite)
            output.write(message.toByteArray())
            output.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun readPipe(pfdRead: ParcelFileDescriptor): String {
        val reader = ParcelFileDescriptor.AutoCloseInputStream(pfdRead)
        val sb = StringBuilder()
        val buffer = ByteArray(4)
        var size = 0
        do {
            try {
                size = reader.read(buffer, 0, buffer.size)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            if (size > 0) {
                sb.append(String(buffer, 0, size))
            }
        } while (size > 0)
        reader.close()
        return sb.toString()
    }


}