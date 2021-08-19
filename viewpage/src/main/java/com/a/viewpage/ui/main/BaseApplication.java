package com.a.viewpage.ui.main;

import android.app.Application;
import android.util.Log;

import java.util.concurrent.CountDownLatch;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("alvin","onCreate");
//        CountDownLatch lock=new CountDownLatch(2);
//
//        new Thread(() -> {
//            try {
//                Thread.sleep(5000);
//                lock.countDown();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        new Thread(() -> {
//            try {
//                Thread.sleep(11000);
//                lock.countDown();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        long time=System.currentTimeMillis();
//        try {
//            lock.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Log.d("alvin","cost:"+(System.currentTimeMillis()-time));
    }
}
