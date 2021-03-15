package com.a.cydia;

import android.app.Application;
import android.content.Context;

public class CydiaApp extends Application {

//    static {
//        System.loadLibrary("dexhook.cy");
//    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        System.loadLibrary("dexhook.cy");

    }
}
