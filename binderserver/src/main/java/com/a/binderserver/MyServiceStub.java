package com.a.binderserver;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import static com.a.binder.IMyService.DESCRIPTOR;
import static com.a.binder.IMyService.TRANSACTION_addCallback;
import static com.a.binder.IMyService.TRANSACTION_say;


public class MyServiceStub extends Binder /**implements IMyService**/
{

//    @Override
//    public IBinder asBinder() {
//        return this;
//    }

    private CallbackProxy callbackProxy;

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case TRANSACTION_say: {
                data.enforceInterface(DESCRIPTOR);
                String str = data.readString();
                Log.d("alvin", "MyService:: Hello, " + str);
                if(callbackProxy!=null){
                    callbackProxy.onSayHello(" [server ---> client] ");
                }
                reply.writeNoException();
                return true;
            }
            case TRANSACTION_addCallback: {
                Log.d("alvin", "MyService:: addCallback ");
                data.enforceInterface(DESCRIPTOR);
                IBinder binder = data.readStrongBinder();
                callbackProxy = new CallbackProxy(binder);

            }
        }
        return super.onTransact(code, data, reply, flags);
    }
}
