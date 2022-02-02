package com.skia.render;

import android.graphics.Bitmap;
import android.view.Surface;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SkiaRender {

    static {
        System.loadLibrary("skia");
        System.loadLibrary("render");
    }


    public native void drawBitmap(@Nullable Surface surface,Bitmap bitmap, int left, int top, int width, int height);


    public native void drawText(@Nullable Surface surface, @NotNull String text, int left, int top, int width, int height);

    public native void drawTextOnBitmap(Bitmap bitmap, @NotNull String text);

}
