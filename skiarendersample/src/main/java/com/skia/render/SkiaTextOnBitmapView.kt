package com.skia.render

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.ImageView

class SkiaTextOnBitmapView : androidx.appcompat.widget.AppCompatImageView {

    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        init()
    }
    private fun init() {
        var bitmap = BitmapFactory.decodeResource(resources, R.mipmap.xx)
        SkiaRender().drawTextOnBitmap(bitmap, "hello world ?")
        setImageBitmap(bitmap)
    }


}



