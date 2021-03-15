#include <jni.h>
#include "include/substrate.h"
#include <android/log.h>
#include <unistd.h>
#include <stdio.h>
#include <fcntl.h>
#include <sys/types.h>
#include <string.h>
#include <sys/stat.h>
#include <stdlib.h>
#include "include/dalvik.h"

#define TAG "alvin"
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__)



//ClassObject* (*oldDvmResolveClass)(ClassObject* referrer, u4 classIdx, bool fromUnverifiedConstant);
//
//ClassObject* newDvmResolveClass(ClassObject* referrer, u4 classIdx, bool fromUnverifiedConstant) {
//
//    ClassObject* obj=  oldDvmResolveClass(referrer,classIdx,fromUnverifiedConstant);
//    LOGE("newDvmResolveClass fromUnverifiedConstant %s, %s",referrer->descriptor,obj->descriptor);
//    return obj;
//}

void* (*oldDvmResolveClass)(void* referrer, u4 classIdx, bool fromUnverifiedConstant);

void* newDvmResolveClass(void* referrer, u4 classIdx, bool fromUnverifiedConstant) {
   void* res= oldDvmResolveClass(referrer, classIdx, true);

//    ClassObject* referrerClass= static_cast<ClassObject *>(referrer);
//    ClassObject* resClass= static_cast<ClassObject *>(res);
//
//    LOGE("newDvmResolveClass fromUnverifiedConstant %s, %s",referrerClass->descriptor,resClass->descriptor);

    return res;
}

//指明要hook的lib ：
MSConfig(MSFilterLibrary,"/system/lib/libdvm.so")
MSConfig(MSFilterExecutable,"com.a.cydia")

MSInitialize {
    LOGE("Cydia Init");
    MSImageRef image;
    //载入lib
    image = MSGetImageByName("/system/lib/libdvm.so");
    if (image != NULL) {
        LOGE("image is not null");
        void * resloveMethd=MSFindSymbol(image, "dvmResolveClass");

        if(resloveMethd != NULL) {
            LOGE("resloveMethd is not null addr is %p", resloveMethd);
            MSHookFunction(resloveMethd, (void*)newDvmResolveClass, (void**)&oldDvmResolveClass);
        } else {
            LOGE("error find dvmResolveClass");
        }
    }
}