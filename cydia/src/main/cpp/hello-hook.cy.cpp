#include <jni.h>
#include <android/log.h>
#include "include/substrate.h"

#define LOG(...) __android_log_print(ANDROID_LOG_DEBUG,"hello",__VA_ARGS__)
extern "C"
{

JNIEXPORT jstring JNICALL Java_com_a_cydia_CydiaMainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    return env->NewStringUTF("Hello ,,,,,");
}
}
//这是HOOK程序的写法，像hook livdvm.so应该这样写：MSConfig(MSFilterLibrary, "libdvm.so");

MSConfig(MSFilterExecutable,"/system/bin/app_process")
//旧的函数地址，为了保留就函数入口，有时候会用到，当然我们这个例子简单，就不用了
char * (* getFromNativeString) (void);

char * newhello(void)
{
    return "hello better tomorrow";
}

//通过so库的绝对路径和函数名，找到其函数的映射地址
void * lookup_symbol(char* libraryname,char* symbolname)
{
    void *handle = dlopen(libraryname,RTLD_GLOBAL | RTLD_NOW);

    if(handle != NULL){
        void *symbol = dlsym(handle,symbolname);

        if(symbol != NULL){
            return symbol;
        } else{
            LOG("dl error: %s",dlerror());
            return NULL;
        }
    } else{
        return NULL;
    }
}

MSInitialize
{
//文件路径记得改为自己的路径
void *symbol = lookup_symbol("/data/data/com.a.hello/lib/libhello.so","getHello");
//这里将旧函数的入口（参数一）指向hello(参数三），然后执行新函数（参数二）
MSHookFunction(symbol,(void *)&newhello,(void **)&getFromNativeString);
};