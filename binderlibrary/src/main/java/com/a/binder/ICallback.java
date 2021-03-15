package com.a.binder;

import android.os.IInterface;
import android.os.RemoteException;

public interface ICallback extends IInterface {

    String DESCRIPTOR = "com.a.binderserver.CallBack";


    int TRANSACTION_onSayHello = android.os.IBinder.FIRST_CALL_TRANSACTION;

    void onSayHello(String msg) throws RemoteException;
}
