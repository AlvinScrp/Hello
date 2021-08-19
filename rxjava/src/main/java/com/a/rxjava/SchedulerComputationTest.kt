package com.a.rxjava

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

class SchedulerComputationTest {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SchedulerComputationTest().test()
            Thread.sleep(50000)
        }
    }


    private var rxEmitter: ObservableEmitter<String>? = null

    private var observable = Observable
        .create<String> { rxEmitter = it }


    fun test() {
        Thread {
            println("test:${Thread.currentThread()} ")
            for (i in 1 until 10001) {
                Observable.just(i)
                    .observeOn(Schedulers.computation())
                    .subscribe { Thread.sleep(100);println("on next:${Thread.currentThread()}  $it") }
            }
        }.start()
    }

}