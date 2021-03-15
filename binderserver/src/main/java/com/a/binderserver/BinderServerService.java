package com.a.binderserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class BinderServerService extends Service {

    private IBinder binder;

    public BinderServerService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new MyServiceStub();
        Log.d("alvin", "BinderServerService onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}