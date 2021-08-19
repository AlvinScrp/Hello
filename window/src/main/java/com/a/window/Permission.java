package com.a.window;

import android.Manifest;

import androidx.fragment.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

public class Permission {

    public static void  check(FragmentActivity activity){
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(
                Manifest.permission.READ_PHONE_STATE,Manifest.permission.GET_ACCOUNTS)
                .subscribe(aBoolean -> {
                    //不管同意或者拒绝，都正常往下走

                }, throwable -> {

                });
    }
}
