package com.a.viewpage.ui.main

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.annotation.RequiresApi

class MyView : View {

    constructor(context: Context) : this(context,null,0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    var text:String?=null

    var focusChangeListener:ViewTreeObserver.OnWindowFocusChangeListener?=null
    private fun init() {
        focusChangeListener=
        (ViewTreeObserver.OnWindowFocusChangeListener {
//            Log.d("alvin","MyView ${text} OnWindowFocusChange hasFocus:$it ")
        })
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var paint =Paint()
        paint.color = Color.parseColor("#29ff0000")

//     Log.d("alvin","measuredHeight: $measuredHeight ,  measuredWidth:  $measuredWidth")

        var oval =  RectF()
//        oval.set(-measuredWidth.toFloat(),-measuredWidth.toFloat(), measuredWidth.toFloat(),-measuredWidth.toFloat())
        oval.set(-800.0f,-800.0f, 600.0f,600.0f)
        canvas?.drawArc(oval, 0f, 360f, true, paint)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
//        Log.d("alvin","MyView ${text} onAttachedToWindow ")
       viewTreeObserver.removeOnWindowFocusChangeListener (focusChangeListener)
       viewTreeObserver.addOnWindowFocusChangeListener (focusChangeListener)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
//        Log.d("alvin","MyView ${text} onDetachedFromWindow ")
        viewTreeObserver.removeOnWindowFocusChangeListener (focusChangeListener)
    }




}