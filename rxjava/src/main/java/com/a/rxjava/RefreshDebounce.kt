package com.a.rxjava

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import java.util.concurrent.TimeUnit

class RefreshDebounce {

    private var rxEmitter: ObservableEmitter<Long>? = null

    private var observable = Observable
        .create<Long> { rxEmitter = it }
        .debounce(1000L, TimeUnit.MILLISECONDS)

    constructor(consumer: (Long) -> Unit? ){
        observable.subscribe { consumer.invoke(it) }
    }

    fun callRefresh() = rxEmitter?.onNext(System.currentTimeMillis())
}