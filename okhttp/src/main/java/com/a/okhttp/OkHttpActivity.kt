package com.a.okhttp

import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_ok_http.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException


class OkHttpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_http)
        btnAsyncGet.setOnClickListener { asyncGetSomething() }
        btnSyncGet.setOnClickListener { syncGetSomething() }
        btnClear.setOnClickListener { tv1.text = "" }
    }

    private fun asyncGetSomething() {
        val url = "https://www.wanandroid.com/banner/json"
//        val url = "https://raw.github.com/square/okhttp/master/README.md"
        val okHttpClient = OkHttpClient()
        val request: Request = Request.Builder()
                .url(url)
                .get() //默认就是GET请求，可以不写
                .build()
        val call: Call = okHttpClient.newCall(request)

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    tv1.text = "onFailure:${e.message}"
                }
            }

            override fun onResponse(call: Call, response: Response) {
                runOnUiThread {
                    tv1.text = "onResponse:${response.body?.string()}"
                }
            }

        })
    }

    private fun syncGetSomething() {
        Thread {
            val url = "https://www.wanandroid.com/banner/json"
            val okHttpClient = OkHttpClient()
            val request: Request = Request.Builder().url(url).get().build()
            val call=okHttpClient.newCall(request);
            val response = call.execute()
            var str = "onResponse:${response.body?.string()}"
            runOnUiThread { tv1.text = str }
        }.start()
    }
}

