package com.a.rxjava;


import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


class RxJavaMainJava {

    public static void main(String[] args) {


        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NotNull ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Hello");
                emitter.onComplete();
            }
        });



        Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello";
            }
        });


    }
}
