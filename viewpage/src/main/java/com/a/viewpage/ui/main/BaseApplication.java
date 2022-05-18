package com.a.viewpage.ui.main;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;

import java.util.concurrent.CountDownLatch;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();



        ProcessLifecycleOwner.get().getLifecycle().addObserver(new DefaultLifecycleObserver() {

            @Override
            public void onCreate(@NonNull LifecycleOwner owner) {
                DefaultLifecycleObserver.super.onCreate(owner);
                Log.d("alvin-app","ProcessLifecycleOwner onCreate:"+owner.toString());
            }

            @Override
            public void onStart(@NonNull LifecycleOwner owner) {
                DefaultLifecycleObserver.super.onStart(owner);
                Log.d("alvin-app","ProcessLifecycleOwner onStart:"+owner.toString());
            }

            @Override
            public void onResume(@NonNull LifecycleOwner owner) {
                DefaultLifecycleObserver.super.onResume(owner);
                Log.d("alvin-app","ProcessLifecycleOwner onResume:"+owner.toString());
            }

            @Override
            public void onPause(@NonNull LifecycleOwner owner) {
                DefaultLifecycleObserver.super.onPause(owner);
                Log.d("alvin-app","ProcessLifecycleOwner onPause:"+owner.toString());
            }

            @Override
            public void onStop(@NonNull LifecycleOwner owner) {
                DefaultLifecycleObserver.super.onStop(owner);
                Log.d("alvin-app","ProcessLifecycleOwner onStop:"+owner.toString());
            }

            @Override
            public void onDestroy(@NonNull LifecycleOwner owner) {
                DefaultLifecycleObserver.super.onDestroy(owner);
                Log.d("alvin-app","ProcessLifecycleOwner onDestroy:"+owner.toString());
            }
        });

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                Log.d("alvin-app","onActivityCreated:"+activity.toString());
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                Log.d("alvin-app","onActivityStarted:"+activity.toString());
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                Log.d("alvin-app","onActivityResumed:"+activity.toString());
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                Log.d("alvin-app","onActivityPaused:"+activity.toString());
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                Log.d("alvin-app","onActivityStopped:"+activity.toString());
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                Log.d("alvin-app","onActivityCreated:"+activity.toString());
            }
        });

    }
}
