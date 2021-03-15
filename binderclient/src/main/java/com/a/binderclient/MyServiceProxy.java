package com.a.binderclient;

import android.content.ContentProvider;
import android.os.IBinder;
import android.os.RemoteException;

import com.a.binder.IMyService;

import static com.a.binder.IMyService.DESCRIPTOR;
import static com.a.binder.IMyService.TRANSACTION_addCallback;
import static com.a.binder.IMyService.TRANSACTION_say;

public class MyServiceProxy /** implements IMyService**/
{
    private IBinder mRemote;  //代表BpBinder

    public MyServiceProxy(IBinder remote) {
        mRemote = remote;
    }

    /**
     * 自定义的sayHello()方法
     **/
//    @Override
    public void sayHello(String str) throws RemoteException {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
            _data.writeInterfaceToken(DESCRIPTOR);
            _data.writeString(str);
            mRemote.transact(TRANSACTION_say, _data, _reply, 0);
            _reply.readException();
        } finally {
            _reply.recycle();
            _data.recycle();
        }
    }

    public void addCallback(CallbackStub callbackStub) throws Exception {



        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
            _data.writeInterfaceToken(DESCRIPTOR);
            _data.writeStrongBinder(callbackStub);
            mRemote.transact(TRANSACTION_addCallback, _data, _reply, 0);
            _reply.readException();
        } finally {
            _reply.recycle();
            _data.recycle();
        }
    }

//    @Override
//    public IBinder asBinder() {
//        return mRemote;
//    }
}
