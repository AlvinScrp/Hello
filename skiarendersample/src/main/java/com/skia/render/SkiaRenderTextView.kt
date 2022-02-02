package com.skia.render

import android.content.Context
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class SkiaRenderTextView : SurfaceView, SurfaceHolder.Callback {

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    lateinit var mHolder: SurfaceHolder
    lateinit var skiaRender: SkiaRender


    private fun init() {
        mHolder = holder
        mHolder.addCallback(this)
        skiaRender = SkiaRender()
    }


    override fun surfaceCreated(p0: SurfaceHolder) {
//        skiaRender.init(p0.surface,width,height)
        skiaRender.drawText(p0.surface, "hello world", 0, 0, width, height);
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
    }
}



