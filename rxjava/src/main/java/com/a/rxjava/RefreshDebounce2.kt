package com.a.rxjava

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

class RefreshDebounce2 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            RefreshDebounce2().callRefresh()
            Thread.sleep(50000)
        }
    }


    private var rxEmitter: ObservableEmitter<String>? = null

    private var observable = Observable
        .create<String> { rxEmitter = it }


    constructor() {
        println("main:${Thread.currentThread()} ")
        observable
            .observeOn(Schedulers.io())
            .subscribe {
                Thread.sleep(Random().nextInt(5).toLong())
                println("on next:${Thread.currentThread()} $it")
            }
    }

    fun callRefresh() {
        Thread {
            println("callRefresh:${Thread.currentThread()} ")
            for (i in 1 until 10001) {
                Thread.sleep(Random().nextInt(5).toLong())
//                Thread {
                    rxEmitter?.onNext("$i ${System.currentTimeMillis()}")
//                }.start()
            }
        }.start()
    }

}