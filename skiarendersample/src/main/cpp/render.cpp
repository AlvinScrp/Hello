#include <jni.h>
#include <string>

#include <SkBitmap.h>

#include <include/core/SkFont.h>
#include "include/core/SkCanvas.h"
#include <include/core/SkPaint.h>
#include <include/core/SkRect.h>
#include <include/core/SkColor.h>
#include <include/core/SkGraphics.h>

#include <android/bitmap.h>
#include <android/native_window.h>
#include <android/native_window_jni.h>

#include <android/log.h>
#include <include/private/SkColorData.h>
#include <include/core/SkSurface.h>


#define TAG "JNI_TAG"
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)

extern "C"
JNIEXPORT void JNICALL
Java_com_skia_render_SkiaRender_drawText(JNIEnv *env, jobject thiz, jobject surface, jstring text, jint left, jint top, jint width,
                                         jint height) {
    ANativeWindow *nativeWindow = ANativeWindow_fromSurface(env, surface);
    ANativeWindow_setBuffersGeometry(nativeWindow, width, height, WINDOW_FORMAT_RGBA_8888);
    ANativeWindow_Buffer *buffer = new ANativeWindow_Buffer();
    ANativeWindow_lock(nativeWindow, buffer, 0);


    SkBitmap bitmap;
    bitmap.setInfo(
            SkImageInfo::MakeS32(buffer->width, buffer->height, SkAlphaType::kPremul_SkAlphaType),
            buffer->stride * 4);
    bitmap.setPixels(buffer->bits);
    SkCanvas *skCanvas = new SkCanvas(bitmap);

    SkPaint paint;
    paint.setColor(SK_ColorYELLOW);
    SkRect rect;
    rect.set(SkIRect::MakeWH(width, height));
    skCanvas->drawRect(rect, paint);

    SkPaint paint2;
    paint2.setColor(SK_ColorRED);
    SkFont skfont(SkTypeface::MakeDefault(), 100);
    skCanvas->drawString(env->GetStringUTFChars(text, 0), 100, height / 2, skfont, paint2);

    ANativeWindow_unlockAndPost(nativeWindow);

}


extern "C"
JNIEXPORT void JNICALL
Java_com_skia_render_SkiaRender_drawBitmap(JNIEnv *env, jobject thiz, jobject surface, jobject sourceBitmap, jint left, jint top,
                                           jint width,
                                           jint height) {

    ANativeWindow *nativeWindow = ANativeWindow_fromSurface(env, surface);
    ANativeWindow_setBuffersGeometry(nativeWindow, width, height, WINDOW_FORMAT_RGBA_8888);
    ANativeWindow_Buffer *buffer = new ANativeWindow_Buffer();
    ANativeWindow_lock(nativeWindow, buffer, 0);


    SkBitmap bitmap;
    bitmap.setInfo(
            SkImageInfo::MakeS32(buffer->width, buffer->height, SkAlphaType::kPremul_SkAlphaType),
            buffer->stride * 4);
    bitmap.setPixels(buffer->bits);
    SkCanvas *skCanvas = new SkCanvas(bitmap);

    SkPaint paint;
    paint.setColor(SK_ColorYELLOW);
    SkRect rect;
    rect.set(SkIRect::MakeWH(width, height));
    skCanvas->drawRect(rect, paint);


    AndroidBitmapInfo info;
    AndroidBitmap_getInfo(env, sourceBitmap, &info);
    int *pixels = NULL;
    AndroidBitmap_lockPixels(env, sourceBitmap, reinterpret_cast<void **>(&pixels));
    SkBitmap bitmap2;
    bitmap2.setInfo(
            SkImageInfo::MakeS32(info.width, info.height, SkAlphaType::kPremul_SkAlphaType),
            info.stride);
    bitmap2.setPixels(pixels);
    skCanvas->drawBitmap(bitmap2, 100, 100, NULL);
    AndroidBitmap_unlockPixels(env, sourceBitmap);

    ANativeWindow_unlockAndPost(nativeWindow);

}

extern "C"
JNIEXPORT void JNICALL
Java_com_skia_render_SkiaRender_drawTextOnBitmap(JNIEnv *env, jobject thiz, jobject sourceBitmap, jstring text) {
    AndroidBitmapInfo info;
    AndroidBitmap_getInfo(env, sourceBitmap, &info);
    int *pixels = NULL;
    AndroidBitmap_lockPixels(env, sourceBitmap, reinterpret_cast<void **>(&pixels));
    SkBitmap bitmap2;
    bitmap2.setInfo(
            SkImageInfo::MakeS32(info.width, info.height, SkAlphaType::kPremul_SkAlphaType),
            info.stride);
    bitmap2.setPixels(pixels);
    SkCanvas *skCanvas = new SkCanvas(bitmap2);
    SkPaint paint2;
    paint2.setColor(SK_ColorGREEN);
    SkFont skfont(SkTypeface::MakeDefault(), 50);
    skCanvas->drawString(env->GetStringUTFChars(text, 0),100, info.height/2,skfont,paint2);

    AndroidBitmap_unlockPixels(env, sourceBitmap);
}