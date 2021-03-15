package com.a.binderserver;

import android.os.IBinder;
import android.os.RemoteException;

import com.a.binder.ICallback;


public class CallbackProxy {
    private IBinder mRemote;

    public CallbackProxy(IBinder mRemote) {
        this.mRemote = mRemote;
    }

    public void onSayHello(String msg) throws RemoteException {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
            _data.writeInterfaceToken(ICallback.DESCRIPTOR);
            _data.writeString(msg);
            mRemote.transact(ICallback.TRANSACTION_onSayHello, _data, _reply, 0);
            _reply.readException();
        } finally {
            _reply.recycle();
            _data.recycle();
        }
    }



}
