package com.a.rxjavaapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rx_java_app.*
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.SocketTimeoutException


class RxJavaAppActivity : AppCompatActivity() {

    private var userName: String = "AlvinScrp"

    private var retrofit:Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(OkHttpClient().newBuilder().apply {
            addInterceptor { chain ->
                Thread.sleep(2000)
                throw SocketTimeoutException("sdsd")
                chain.proceed(chain.request())
            }
        }.build())
        .build()

    private var service:IService = retrofit.create(IService::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java_app)

        btnOkHttp.setOnClickListener { okhttp() }
        btnRetrofit.setOnClickListener { retrofit() }
        btnRetrofitAndRxJava.setOnClickListener { retrofitAndRxJava() }


    }

    private fun okhttp() {
        val request = Request.Builder()
            .url("https://api.github.com/users/$userName").get().build()
        val okHttpClient = OkHttpClient()
        var call = okHttpClient.newCall(request)
        call.enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {}
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if (response.code == 200) {
                    var responseString = response.body?.string();
                    var user = Gson().fromJson(responseString, UserBean::class.java)
                    Handler(Looper.getMainLooper()).post { tv.text = "OkHttp: \n ${user.url}" }
                }
            }
        })
    }

    private fun retrofit() {
        service.userInfo(userName).enqueue(object : Callback<UserBean> {
            override fun onFailure(call: Call<UserBean>, t: Throwable) {}
            override fun onResponse(call: Call<UserBean>, response: Response<UserBean>) {
                tv.text = "retrofit: \n ${response.body()?.url}"
            }
        })

    }

    private fun retrofitAndRxJava() {
        service.userInfoRx(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer { tv.text = "retrofitAndRxJava: \n ${it.url }"  },Throwable::printStackTrace)

    }
}

