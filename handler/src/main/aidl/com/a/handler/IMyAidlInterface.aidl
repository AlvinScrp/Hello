// IMyAidlInterface.aidl
package com.a.handler;

// Declare any non-default types here with import statements
import android.os.ParcelFileDescriptor;
import com.a.handler.ICallback;


interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void addFd(ParcelFileDescriptor pfd,ICallback callBack);
    void addFd(ICallback callBack);
}