package com.a.rxjava

import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.internal.fuseable.QueueFuseable
import io.reactivex.internal.fuseable.QueueSubscription
import io.reactivex.internal.observers.LambdaObserver
import io.reactivex.internal.operators.observable.ObservableJust
import io.reactivex.internal.queue.SpscArrayQueue
import io.reactivex.internal.queue.SpscLinkedArrayQueue
import io.reactivex.internal.util.Pow2
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

//import java.util.concurrent.Callable
//import java.util.concurrent.Executors
//import java.util.concurrent.ScheduledThreadPoolExecutor
//import java.util.concurrent.ThreadPoolExecutor
//import java.util.concurrent.TimeUnit
//import java.util.concurrent.atomic.AtomicLong

class RxJavaMain {

    companion object {

//        fun calculateCount(): Int {
//            var values = arrayOf(1, 2, 3, 4, 5)
//            var count = 0;
//            for (value in values) {
//                if (value > 0) {
//                    count++
//                }
//            }
//            return count
//        }
//
//        fun calculateCount2(): Int {
//            var values = arrayOf(1, 2, 3, 4, 5)
//            return values.filter { it > 3 }.count()
//        }

        @JvmStatic
        fun main(args: Array<String>) {
            println("sdsdwwwwsdsdsd")


            Observable.create<Int> {
                println("in create :${Thread.currentThread()}");
                for (i in 1 until 10001) {
                    it.onNext(1)
                }
            }
//                .observeOn(Schedulers.io())/**/

//                .filter { it > 10 }
                .subscribeOn(Schedulers.newThread())
                .subscribe {
                    println("on next:${Thread.currentThread()} $it")}
//            var executor: ScheduledThreadPoolExecutor = Executors.newScheduledThreadPool(1) as ScheduledThreadPoolExecutor
//            executor.scheduleWithFixedDelay({
//                Thread.sleep(2000)
//                println(System.currentTimeMillis())
//            }, 0, 1000, TimeUnit.MILLISECONDS)
//            for (i in 0..10) {
//                executor.execute {
//                    Thread.sleep(1000)
//                    println(System.currentTimeMillis())
//                }
//            }
//            AndroidSchedulers.mainThread()
//
//            Observable.just("Hello")

//            Observable.range(1, 10)
//                .count()
//                .toObservable()
//                .subscribe { println(it) }

//            Observable.fromCallable { 3 }
//                .flatMap { Observable.fromArray(it + 1, it + 2) }
//                .subscribe { println(it) }
//
//            Flowable.range(1,5)
//                .observeOn(Schedulers.newThread())
//                .subscribe { print("$it,") }


//            var qs: QueueSubscription<*>? = null
//            Flowable.intervalRange(1, 5, 0, 100, TimeUnit.MILLISECONDS)
//            Flowable.rangeLong(1, 5)
//                .map { it + 3 }
//                .observeOn(Schedulers.newThread())
//                .subscribe(object : FlowableSubscriber<Long> {
//                    override fun onSubscribe(s: Subscription) {
//                        if (s is QueueSubscription<*>) {
//                            s.requestFusion(QueueFuseable.ASYNC)
//                            qs = s
//                        }
//                        s.request(Long.MAX_VALUE)
//                    }
//
//                    override fun onNext(t: Long?) {
////                        var value = qs?.poll()
////                        println("onNext:$t poll:$value")
//                        println("onNext:$t ")
//
//
//                        while (true) {
//                            var value = qs?.poll()
//                            if (value == null) {
//                                break
//                            }
//                            println("onNext poll:$value")
//                        }
//
//                    }
//
//                    override fun onError(t: Throwable?) {
//                        println("onError:$t")
//                    }
//
//                    override fun onComplete() {
//                        println("onComplete")
//                    }
//
//                })

//            var qs: QueueSubscription<*>? = null
//            Flowable.rangeLong(1, 5)
//                .map { it + 0 }
//                .subscribe(object : FlowableSubscriber<Long> {
//                    override fun onSubscribe(s: Subscription) {
//                        if (s is QueueSubscription<*>) {
//                            var mode = s.requestFusion(QueueFuseable.SYNC or QueueFuseable.BOUNDARY)
//                            println("onSubscribe fusion mode result: $mode" )
//                            qs = s
//                        }
//                        s.request(Long.MAX_VALUE)
//                    }
//
//                    override fun onNext(t: Long?) {
////                        var value = qs?.poll()
////                        println("onNext:$t poll:$value")
//                        println("onNext:$t ")
//
//
//                        while (true) {
//                            var value = qs?.poll()
//                            if (value == null) {
//                                break
//                            }
//                            println("onNext poll:$value")
//                        }
//
//                    }
//
//                    override fun onError(t: Throwable?) {
//                        println("onError:$t")
//                    }
//
//                    override fun onComplete() {
//                        println("onComplete")
//                    }
//
//                })

//            var qs: QueueSubscription<*>? = null
//            var executor = Executors.newSingleThreadExecutor()
//            Flowable.rangeLong(1, 5)
//                .map { println("int map: value $it,${Thread.currentThread()}");it }
//                .subscribe(object : FlowableSubscriber<Long> {
//                    override fun onSubscribe(s: Subscription) {
//                        if (s is QueueSubscription<*>) {
//                            var mode = s.requestFusion(QueueFuseable.SYNC)
//                            println("onSubscribe fusion mode result: $mode")
//                            qs = s
//                        }
//                        s.request(Long.MAX_VALUE)
//                    }
//
//                    override fun onNext(t: Long?) {
////                        executor.submit { qs?.poll() }
////                        qs?.poll()
//                    }
//
//                    override fun onError(t: Throwable?) {
//                        println("onError:$t")
//                    }
//
//                    override fun onComplete() {
//                        println("onComplete")
//                    }
//                })


//            Observable.fromCallable { "Hello" }
//            Observable.create<String> {emitter-> emitter.onNext("Hello") }
//
//            Observable.fromCallable(Callable {  })


//            var refreshDebounce = RefreshDebounce { println("refresh:$it") }
//            refreshDebounce.callRefresh()

//            println("in main:${Thread.currentThread()}")
//            Observable.create<Int> {
//                    println("in create :${Thread.currentThread()}");
//                    for(i in 1 until 10001) {
//                        it.onNext(1)
//                    }}
////                .observeOn(Schedulers.io())/**/
//
////                .filter { it > 10 }
//                .subscribeOn(Schedulers.newThread())
//                .subscribe { println("on next:${Thread.currentThread()} $it") }


//            println("in main:${Thread.currentThread()}")
//            Observable.create<Int> {
//                println("in create :${Thread.currentThread()}");
//                for(i in 1 until 10001) {
//                    it.onNext(1)
//                }}
//                .observeOn(Schedulers.computation())/**/
//
////                .filter { it > 10 }
////                .subscribeOn(Schedulers.newThread())
//                .subscribe { println("on next:${Thread.currentThread()} $it") }


//            RefreshDebounce2().callRefresh()


//                .subscribe (object:Observer<Int>{
//                    override fun onSubscribe(d: Disposable) { }
//                    override fun onNext(t: Int) { }
//                    override fun onError(e: Throwable) { }
//                    override fun onComplete() { }
//                })

//            Observable
//                .fromArray(1, 2, 3, 4)
//                .observeOn(Schedulers.newThread())
//                .map { it * 5 }
//                .subscribeOn(Schedulers.io())
//                .subscribe { println(it) }


//            println(Pow2.roundToPowerOfTwo(3))

                    //            var spscArrayQueue = SpscLinkedArrayQueue<Int>(3)
//            for (i in 1..1000) {
//                var offered = spscArrayQueue.offer(i)
//                println("spscArrayQueue.offer:$i , $offered")
//
//            }
//            var count = 0

//            Flowable.create(
//                FlowableOnSubscribe<Int> { emitter ->
//                    for (i in 1..1000) {
////                            Thread.sleep(1)
////                            println("emitter.onNext:$i ")
//                        emitter.onNext(i)
//                    }
////                    }, BackpressureStrategy.LATEST)
////                    }, BackpressureStrategy.MISSING)
////                    }, BackpressureStrategy.ERROR)
////                    }, BackpressureStrategy.DROP)
//                }, BackpressureStrategy.BUFFER
//            )
//                .subscribe { println(it) }

//            MyFlowable(
//                    FlowableOnSubscribe<Int> { emitter ->
//                        for (i in 1..1000) {
//                            Thread.sleep(1)
////                            println("emitter.onNext:$i ")
//                            emitter.onNext(i)
//                        }
//                    })

                    //被观察者在主线程中，每1ms发送一个事件
//            Flowable.interval(10, TimeUnit.MILLISECONDS)
////                    .onBackpressureLatest()
//                .onBackpressureDrop()
////                    //将观察者的工作放在新线程环境中
//                .observeOn(Schedulers.newThread(), false, 4) //观察者处理每1000ms才处理一个事件
//
//                .subscribe({
//                    Thread.sleep(1000)
//                    println("Flowable interval subscribe:$it , ${count++} ${Thread.currentThread()}")
//                }, Throwable::printStackTrace)


//            Observable.interval(1, TimeUnit.MILLISECONDS)
//                    .observeOn(Schedulers.newThread()) //观察者处理每1000ms才处理一个事件
//                    .subscribe {
//                        Thread.sleep(1000)
//                        println("Observable interval subscribe:$it")
//                    }

//            Observable.just(1).map { it + 2 }.map { it * 3 }.map { it - 4 }
//                    .observeOn(Schedulers.newThread()) //观察者处理每1000ms才处理一个事件
//                    .subscribe {
//                        Thread.sleep(1000)
//                        println(it)
//                    }

                    //            Flowable.just(1, 2, 3, 4, 5)
//                    .subscribe {
//                        println("Flowable next : $it")
//                    }
//
//            Observable.just(1, 2, 3, 4, 5)
//                    .reduce { t1: Int, t2: Int -> t1 + t2 }
//                    .subscribe { println("reduce next : $it") }

//            Thread.sleep(400000)

//            RxJavaPlugins.setOnObservableAssembly { println("OnObservableAssembly: $it"); return@setOnObservableAssembly it }

//            Observable.just(1)
//            Observable.just(1, 2, 3, 4)
//                    .map { it + 1 }
//                    .subscribe(object : Observer<Int> {
//                        override fun onComplete() {
//                            println("just onComplete")
//                        }
//
//                        override fun onSubscribe(d: Disposable) {
//                            println("just onSubscribe Disposable: $d ")
//                            if (d is QueueFuseable<*>) {
//                                d.requestFusion(QueueFuseable.SYNC)
//                                while (true) {
//                                    var value: Any? = d.poll() ?: break
//                                    if (value is Int) onNext(value)
//                                }
//                            }
//                        }
//
//                        override fun onNext(t: Int) {
//                            println("just onNext:${t}")
//                        }
//
//                        override fun onError(e: Throwable) {
//                            e.printStackTrace()
//                        }
//
//                    })


//            println("mainThread ${Thread.currentThread()}")
//
//            Observable.just("a", "b")
//                    .subscribeOn(Schedulers.newThread())
//                    .observeOn(Schedulers.newThread())
//                    .subscribe(object : Observer<String> {
//                        override fun onComplete() {
//                            println("just onComplete")
//                        }
//
//                        override fun onSubscribe(d: Disposable) {
//                            println("onSubscribe ${Thread.currentThread()}")
//                        }
//
//                        override fun onNext(t: String) {
//                            println("onNext ${Thread.currentThread()}")
//                        }
//
//                        override fun onError(e: Throwable) {
//                            e.printStackTrace()
//                        }
//                    })


//            Observable.just("a", "b").subscribeOn(Schedulers.newThread())
//                    .flatMap {
//                        Observable.fromCallable { Thread.sleep(1000); it + "x" }
//                                /**.delay(1000, TimeUnit.MILLISECONDS)**/
//                                .observeOn(Schedulers.newThread())
//                    }
////                    .map { it + 1 }
//                    .subscribe(object : Observer<String> {
//                        override fun onComplete() {
//                            println("just onComplete")
//                        }
//
//                        override fun onSubscribe(d: Disposable) {
//                            println("just onSubscribe Disposable: $d ")
////                            if (d is QueueFuseable<*>) {
////                                d.requestFusion(QueueFuseable.SYNC)
////                                while (true) {
////                                    var value: Any? = d.poll() ?: break
////                                    if (value is Int) onNext(value)
////                                }
////                            }
//                        }
//
//                        override fun onNext(t: String) {
//                            println("just onNext:${t}")
//                        }
//
//                        override fun onError(e: Throwable) {
//                            e.printStackTrace()
//                        }
//
//                    })
//            Thread.sleep(5000000)

//            Observable.just(1, 2, 3, 4)
//                    .observeOn(Schedulers.newThread())
//                    .map { it + 1 }
//                    .subscribe(object : Observer<Int> {
//                        override fun onComplete() {
//                            println("just onComplete")
//                        }
//
//                        override fun onSubscribe(d: Disposable) {
//                            println("just onSubscribe Disposable: $d ")
//                            if (d is QueueFuseable<*>) {
//                                d.requestFusion(QueueFuseable.SYNC)
//                                while (true) {
//                                    var value: Any? = d.poll() ?: break
//                                    if (value is Int) onNext(value)
//                                }
//                            }
//                        }
//
//                        override fun onNext(t: Int) {
//                            println("just onNext:${t}")
//                        }
//
//                        override fun onError(e: Throwable) {
//                            e.printStackTrace()
//                        }
//
//                    })

//            Observable.just(1,2,3,4)
//                    .subscribeOn(Schedulers.newThread()).subscribe {  }

//            ObservableJust(3).subscribe { println(it) }
//
//
//            Observable.range(1, 10)
//                    .filter { v -> v % 3 == 0 }
//                    .filter { v -> v % 2 == 0 }
//                    .map { v -> v + 1 }
//                    .map { v -> v * v }
//                    .subscribe(System.out::println);
//
//            Observable
//                    .create(object : ObservableOnSubscribe<Int> {
//                        override fun subscribe(emitter: ObservableEmitter<Int>) {
//                            emitter.onNext(1)
//                            emitter.onComplete()
//                        }
//                    })
//                    .subscribe(object : Observer<Int> {
//                        override fun onComplete() {
//                            println("create onComplete")
//                        }
//
//                        override fun onSubscribe(d: Disposable) {
//                            println("create onSubscribe Disposable: $d ")
//                        }
//
//                        override fun onNext(t: Int) {
//                            println("create onNext:${t}")
//                        }
//
//                        override fun onError(e: Throwable) {
//                            e.printStackTrace()
//                        }
//
//                    })

//            Observable.just(1).subscribe { println("just:${it}") }


//            var arr= arrayListOf(1,2,3,4)
//            Observable.fromIterable(arr).subscribe { println("fromArray:${it}") }

//            Observable.fromCallable { 1 }.subscribe { println("fromCallable:${it}") }


//            println(System.currentTimeMillis())
//            var future = Executors.newCachedThreadPool().submit(Callable { Thread.sleep(5000);return@Callable 1 })
//            Observable
//                    .fromFuture(future, 10000, TimeUnit.MILLISECONDS)
////                    .observeOn(Schedulers.io())
//                    .subscribeOn(Schedulers.io())
//                    .subscribe { println("fromFuture:${it}") }
//            println(System.currentTimeMillis())

//            Observable.defer(object : Callable<ObservableSource<out Any>> {
//                override fun call(): ObservableSource<out Any> {
//                    return Observable.just(1)
//                }
//            }).subscribe { println("from defer:${it}") }


//            Observable.timer(1000,TimeUnit.MILLISECONDS).subscribe { println("timer onNext: $it")  }
//            Observable.interval(1000,TimeUnit.MILLISECONDS).subscribe { println("interval onNext: $it")  }
//            Observable.intervalRange(35,5,3000,1000,TimeUnit.MILLISECONDS)
//                    .subscribe { println("intervalRange onNext: $it")  }

//            Thread.sleep(10000);


//            Observable.range(3,10).subscribe {  println("range onNext: $it") }
//            Observable.rangeLong(3,10).subscribe {  println("rangeLong onNext: $it") }
//            Observable.empty<Int>().subscribe(object :Observer<Int>{
//                override fun onComplete() {
//                   println("Observable.empty onComplete()")
//                }
//
//                override fun onSubscribe(d: Disposable) {
//
//                }
//
//                override fun onNext(t: Int) {
//
//                }
//
//                override fun onError(e: Throwable) {
//
//                }
//            })
//
//            Observable.error<Int>(NullPointerException("haha")).subscribe(object :Observer<Int>{
//                override fun onComplete() {
//                    println("Observable.error onComplete()")
//                }
//
//                override fun onSubscribe(d: Disposable) {
//
//                }
//
//                override fun onNext(t: Int) {
//
//                }
//
//                override fun onError(e: Throwable) {
//                    println("Observable onError $e")
//                }
//            })
//            Observable.just(1).map(object : Function<Int, Int> {
//                override fun apply(t: Int): Int {
//                    return t + 1
//                }
//            }).subscribe { println("map: $it") }
//
//            Observable.just(1).flatMap(object : Function<Int, ObservableSource<Int>> {
//                override fun apply(t: Int): ObservableSource<Int> {
//                    return Observable.just(t + 1)
//                }
//            }).subscribe { println("flatMap: $it") }

//            var list = listOf(1, 2, 3, 4, 5)
//            Observable.just(list)
//                    .flatMap { Observable.fromIterable(it) }
//                    .subscribe { println(it) }

//            Observable.fromArray(1, 2)
//                    .flatMap { Observable.just("${it}a", "${it}b") }
////                    .flatMap { Observable.just("${it}a").delay(1, TimeUnit.MILLISECONDS) }
//                    .subscribe(object : Observer<String> {
//                        override fun onComplete() {
//                        }
//
//                        override fun onSubscribe(d: Disposable) {
//                        }
//
//                        override fun onNext(t: String) {
//                            println(t)
//                        }
//
//                        override fun onError(e: Throwable) {
//                        }
//
//                    })

//            var service = Retrofit.Builder()
//                    .baseUrl("https://www.wanandroid.com/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .build().create(IService::class.java)
//
//            Observable.fromArray(0, 1)
//                    .flatMap { service.banner() }
//                    .subscribe {
//                        println(Gson().toJson(it.data[0]))
//                    }

//            Thread.sleep(5000000)

//            Flowable.just(1,2,3,4,5)
//                    .filter { it>3 }
//                    .filter { it>4 }
//                    .subscribe { println(it) }

//            Observable.just("aaaa", "bbbb", "cccc", "dddd")
//                    .flatMap {
//                        var delay: Long = if (it.contains("a")) 100 else 10;
//                        Observable.fromIterable(it.toCharArray().asList()).delay(delay, TimeUnit.MILLISECONDS)
//                    }
//                    .subscribe { println(it) }
//
//            Observable.just("aaaa", "bbbb", "cccc", "dddd")
//                    .concatMap {
//                        var delay: Long = if (it.contains("a")) 100 else 10;
//                        Observable.fromIterable(it.toCharArray().asList()).delay(delay, TimeUnit.MILLISECONDS)
//                    }
//                    .subscribe { println(it) }
//
//            Thread.sleep(10000)


//            Observable.just(1, 2, 3, 4, 5)
//                    .buffer(2, 1)
//                    .subscribe { println("buffer: $it") }

//            Observable.just(5, 2, 3, 4, 1, 6, 8, 9, 7, 10)
//                    .groupBy { it % 3 }
//                    .sorted { a, b -> (a.key ?: 0) - (b.key ?: 0) }
//                    .flatMap { it }
//                    .subscribe { println("groupby value:$it  ${it % 3}") }

//            Observable.just(1, 2, 3, 4, 5)
//                    .scan { t1: Int, t2: Int -> t1 + t2 }
//                    .subscribe { println("scan $it") }
//
//            Observable.just(1, 2, 3, 4, 5)
//                    .window(2)
//                    .subscribe(object : Observer<Observable<Int>> {
//                        override fun onComplete() {
//                        }
//
//                        override fun onNext(ob: Observable<Int>) {
//                            println("window onNext $ob")
//                            ob.subscribe { println("window $it") }
//                        }
//
//                        override fun onSubscribe(d: Disposable) {
//                        }
//
//                        override fun onError(e: Throwable) {
//                        }
//
//                    })

//            Observable.concat(
//                    Observable.just(1).delay(2, TimeUnit.SECONDS),
//                    Observable.just("a").delay(1, TimeUnit.SECONDS),
//                    Observable.just("c"))
//                    .subscribe { println("concat: $it") }

//            Observable.concatArray(
//                    Observable.just(1).delay(2, TimeUnit.SECONDS),
//                    Observable.just("a").delay(1, TimeUnit.SECONDS),
//                    Observable.just("c"))
//                    .subscribe { println("concatArray: $it") }


//            Observable.merge(
//                    Observable.just(1).delay(2, TimeUnit.SECONDS),
//                    Observable.just("a").delay(1, TimeUnit.SECONDS),
//                    Observable.just("c"))
//                    .subscribe { println("merge: $it") }

//            Thread.sleep(6000);

//            Observable.zip(
//                    Observable.just(1, 2, 3),
//                    Observable.just("a", "b", "c", "d", "e"),
//                    BiFunction<Int, String, String> { num, letter -> "$num $letter" } )
//                    .subscribe { println("zip next:  $it") }

//            println(System.currentTimeMillis())
//            Observable.combineLatest(
//                    Observable.intervalRange(1, 5, 0,1000,TimeUnit.MILLISECONDS).map { "A$it " },
//                    Observable.intervalRange(1, 5, 0,2000,TimeUnit.MILLISECONDS).map { "B$it " },
//                    BiFunction<String, String, String> { s1, s2 -> "$s1 $s2" } )
//                    .subscribe { println("zip next:  $it ${System.currentTimeMillis()}") }
//
//            Thread.sleep(13000);

//            Observable.just(1, 2, 3, 4, 5)
//                    .reduce { t1: Int, t2: Int -> t1 + t2 }
//                    .subscribe { println("reduce next : $it") }
//
//            Observable.just(1, 2, 3, 4, 5)
//                    .collect(Callable { AtomicInteger(1) }, BiConsumer { t1, t2 -> t1.set(t1.get() + t2) })
//                    .toObservable()
//                    .subscribe { println("collect next : $it") }

//            Observable.just(1, 2, 3, 4, 5)
//                    .startWith(0)
//                    .startWithArray(-2,-1)
//                    .subscribe { println("startWith next : $it") }


//                    Observable.just("a", "b", "c", "d", "e").count().toObservable()
//                    .subscribe { println("count next : $it") }


//            SingleToObservable(Single.just(1))
//                    .subscribe(object : Observer<Int> {
//                        override fun onComplete() {
//                            println("just onComplete")
//                        }
//
//                        override fun onSubscribe(d: Disposable) {
//                            println("just onSubscribe Disposable: $d ")
//                            if (d is DeferredScalarDisposable<*>) {
//                                d.requestFusion(QueueFuseable.ASYNC)
////                                d.lazySet(16)
////                                while (true) {
////                                    var value: Any? = d.poll() ?: break
////                                    if (value is Int) onNext(value)
////                                }
//                            }
//                        }
//
//                        override fun onNext(t: Int) {
//                            println("just onNext:${t}")
//                        }
//
//                        override fun onError(e: Throwable) {
//                            e.printStackTrace()
//                        }
//
//                    }
//                    )

//            Observable.just(1, 2)
//                    .doOnEach { println("doOnEach ${it.value}") }
//                    .doOnNext { println("doOnNext ${it}") }
//                    .doAfterNext { println("doAfterNext ${it} ----") }
//                    .doOnComplete { println("complete") }
//                    .doOnSubscribe { println("doOnSubscribe it.isDisposed:${it.isDisposed}") }
//                    .doFinally { println("doFinally") }
//                    .subscribe { println("at last next : $it") }

//            Observable.concat<String>(
//                    Observable.fromCallable<String> { throw NullPointerException("aaa") }.onErrorReturn { it.message },
//                    Observable.fromCallable<String> { throw IllegalArgumentException("bbb") }.onErrorReturn { it.message })
//                    .subscribe { println("onErrorReturn onNext: $it") }


//            Observable.just(1,2,3,4,5)
//                    .filter { it>3 }
//                    .subscribe { println("filter:${it}") }

//            Observable.just(1,2,"a","b")
//                    .ofType(String::class.java)
//                    .subscribe { println("filter:${it}") }

//            Observable.just(1,2,"a","b")
//                    .skip(1)
//                    .subscribe { println("skip:${it}") }

//            Observable.just(1,2,"a","b",1,2)
//                    .distinct()
//                    .subscribe { println("distinct:${it}") }

//            Observable.just(1,1,2,2,"a","b",1,2)
//                    .distinctUntilChanged()
//                    .subscribe { println("distinctUntilChanged:${it}") }


//            Observable.just("a", "b", "c", "d")
//                    .take(3)
//                    .subscribe { println("take:${it}") }

//            println("debounce:0 ${System.currentTimeMillis()}")
//            Observable.merge(
//                    Observable.just(1),
//                    Observable.just(2).delay(1000,TimeUnit.MILLISECONDS),
//                    Observable.just(3).delay(3000,TimeUnit.MILLISECONDS),
//                    Observable.just(4).delay(9000,TimeUnit.MILLISECONDS) )
//                    .debounce(1000, TimeUnit.MILLISECONDS)
//                    .subscribe { println("debounce:$it ${System.currentTimeMillis()}") }

//            Thread.sleep(66000)
//            Observable.just(1,2,3,4,5)
//                    .firstElement()
//                    .subscribe { println("firstElement:$it") }
//
//            Observable.just(1,2,3,4,5)
//                    .lastElement()
//                    .subscribe { println("lastElement:$it") }
//
//
//            Observable.just("a", "b", "c", "d")
//                    .elementAt(2)
//                    .subscribe { println("lastElement:$it") }

//            Observable.just("a", "b", "c", "d")
//                    .elementAtOrError(5)
//                    .toObservable()
//                    .onErrorReturn { if(it is NoSuchElementException) "NoSuchElementException" else "haha"}
//                    .subscribe( { println("elementAtOrError:$it") },Throwable::printStackTrace)

//            Observable.just("a", "b", "c", "d")
//                    .all {
//                      return@all  (it  is String) }
//                    .toObservable()
//                    .subscribe { println("all:$it") }


//            Observable.just(1, 2, 3, 4, 5)
//                    .takeWhile { it <=4 }
//                    .subscribe { println("takeWhile:$it") }
//
//            Observable.just(5,1, 2, 3, 4, 5)
//                    .skipWhile { it < 2 }
//                    .subscribe { println("skipWhile:$it") }
//
//            Thread.sleep(10000)

//            Observable.sequenceEqual(
//                    Observable.just(1, 2, 3),
//                    Observable.just(1, 2, 3)
//            ).toObservable().subscribe { println("sequenceEqual:$it") }
//
//            Observable.just(1, 2, 3)
//                    .contains(3)
//                    .toObservable()
//                    .subscribe { println("contains:$it") }
//
//            Observable.create<Int> { it.onComplete() }
//                    .isEmpty.toObservable().subscribe { println("isEmpty:$it") }
//
//            Observable.create<Int> { it.onComplete() }
//                    .defaultIfEmpty(333).subscribe { println("defaultIfEmpty:$it") }
//
//            var observables = listOf(
//                    Observable.just(1, 2, 3),
//                    Observable.just("a", "b", "c"))
//            Observable.amb(observables).subscribe { println("amb next:$it") }


        }

    }
}

