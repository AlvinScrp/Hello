package com.a.binderclient;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.a.binder.ICallback;

public class CallbackStub extends Binder /** implements ICallback **/{


//    @Override
//    public IBinder asBinder() {
//        return this;
//    }

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(ICallback.DESCRIPTOR);
                return true;
            }
            case ICallback.TRANSACTION_onSayHello: {
                data.enforceInterface(ICallback.DESCRIPTOR);
                String str = data.readString();
//                onSayHello(str);
                Log.d("alvin", "onSayHello callBack:" + str);
                reply.writeNoException();
                return true;
            }
        }

        return super.onTransact(code, data, reply, flags);
    }

//    @Override
//    public void onSayHello(String msg) throws RemoteException {
//        Log.d("alvin", "onSayHello callBack:" + msg);
//    }
}
