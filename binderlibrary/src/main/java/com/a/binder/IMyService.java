package com.a.binder;

import android.os.RemoteException;

public interface IMyService /**extends IInterface**/
{

    String DESCRIPTOR = "com.a.binderserver.MyServer";

    void sayHello(String str) throws RemoteException;

    void addCallBack(ICallback callBack);

    int TRANSACTION_say = android.os.IBinder.FIRST_CALL_TRANSACTION;
    int TRANSACTION_addCallback = android.os.IBinder.FIRST_CALL_TRANSACTION + 1;
}