package com.a.rxjava

import io.reactivex.*
import io.reactivex.schedulers.Schedulers

class FlowableCreateWithBackpressureStrategy {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
//            var queue = SpscArrayQueue<Int>(9)
//            for (i in 1..50) {
//                if(!queue.offer(i)) {
//                    println(i)
//                    break
//                }
//
//            }
//            println(Pow2.roundToPowerOfTwo(5))
//            println(Pow2.roundToPowerOfTwo(6))
//            println(Pow2.roundToPowerOfTwo(9))
            var flowableOnSubscribe = FlowableOnSubscribe<Int> {
                for (i in 1..16) {
                    Thread.sleep(1)
                    it.onNext(i)
                }
            }
//            Flowable.create(flowableOnSubscribe, BackpressureStrategy.DROP)
//                .observeOn(Schedulers.newThread(), false, 6)
//                .subscribe { Thread.sleep(1);print("$it,") }
//            Flowable.intervalRange(1,50,0,1,TimeUnit.MILLISECONDS)
//                .onBackpressureDrop()
//                .observeOn(Schedulers.newThread(), false, 6)
//                .subscribe { Thread.sleep(2);print("$it,") }

//            FlowableObserveOn2(FlowableRange2(1,100),5)
//            FlowableRange2(1,20)
//                .observeOn(Schedulers.newThread(), false, 7)
//                .subscribe { Thread.sleep(2);print("$it,") }

//            FlowableObserveOn2(FlowableRange2(1,20),5)
//                .subscribe { Thread.sleep(2);print("$it,") }


//            Flowable.create(flowableOnSubscribe, BackpressureStrategy.LATEST)
//                .observeOn(Schedulers.newThread(), false, 4)
//                .subscribe { Thread.sleep(4);print("$it,") }
//
//            Flowable.just(1).subscribe { print("$it,") }

            FlowableCreateWithLatest(flowableOnSubscribe).map { }



            FlowableCreateWithLatest(flowableOnSubscribe)
                .observeOn(Schedulers.newThread(), false, 4)
                .subscribe { Thread.sleep(4);print("$it,") }

            Observable.fromArray(1, 2, 3)
                .map { it + 1 }
                .filter { it < 3 }
                .subscribe { println(it) }
//
//            FlowableObserveOn2(Flowable.create(flowableOnSubscribe, BackpressureStrategy.DROP), 6)
//                .subscribe(object :FlowableSubscriber<Int>{
//                    override fun onSubscribe(s: Subscription) { s.request(Long.MAX_VALUE) }
//                    override fun onNext(t: Int?) {Thread.sleep(3);print("$t,") }
//                    override fun onError(t: Throwable?) { t?.printStackTrace() }
//                    override fun onComplete() {}
//                })

//                .subscribe { Thread.sleep(3);print("$it,") }
            Thread.sleep(1000)
        }
    }


}