package com.a.viewpage.ui.main

import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.annotation.RequiresApi

class MyTextView : androidx.appcompat.widget.AppCompatTextView {

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    fun updateText(str: String) {
        flagText = str;
        text = str
    }

    var flagText: String? = null

    private var mView: View? = null
    private var mRect: Rect? = null
    private var isShowHalf = false //是否显示出50%
    private var comeInTime = System.currentTimeMillis() //view进入屏幕的时间
    private var focusChangeNum = 0 //第一次失去焦点时上传一次数据


    var focusChangeListener: ViewTreeObserver.OnWindowFocusChangeListener? = null
    var onScrollChangeListener: ViewTreeObserver.OnScrollChangedListener? = null
    private fun init() {
        mView = this
        mRect = Rect()
        isShowHalf = false

        focusChangeListener =
                ViewTreeObserver.OnWindowFocusChangeListener { hasFocus ->
                    if (hasFocus) {
                        comeInTime = System.currentTimeMillis()
                        focusChangeNum = 0
                    } else {
                        if (mView != null && mView!!.isShown) {
                            focusChangeNum += 1
                            collectViewData()
                        } else if (focusChangeNum < 2) {
//                    focusChangeNum += 1;
//                    collectViewData();
                        }
                    }
                }

        onScrollChangeListener = ViewTreeObserver.OnScrollChangedListener {

            if (!isShowHalf && mView != null && mRect != null) {
                if (mView!!.isShown && mView!!.visibility == View.VISIBLE && mView!!.getGlobalVisibleRect(mRect)) {
                    var rectHeight = mRect!!.height()
                    var measuredHeight = mView!!.measuredHeight
                    if (mRect!!.height() > mView!!.measuredHeight * 0.5f) {
                        //组件显示超过多少算有效曝光
                        isShowHalf = true
                    }
//                    Log.d("alvin", " MyTextView ${flagText} OnScrollChanged hasWindowFocus:${hasWindowFocus()} isShown:${isShown}  rectHeight:$rectHeight  measuredHeight:$measuredHeight")

                }
            }

        }

    }
//    var changeFocusManual=false

    fun changeFocusManual(hasFocus: Boolean) {
//       changeFocusManual=true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                viewTreeObserver.removeOnWindowFocusChangeListener(focusChangeListener)
            }
//            Log.d("alvin", "MyTextView ${flagText} changeFocusManual  hasFocus:${hasFocus}")
            focusChangeListener?.onWindowFocusChanged(hasFocus)

        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        isShowHalf = false
        comeInTime = System.currentTimeMillis()
//        Log.d("alvin", "MyTextView ${flagText} onAttachedToWindow  hasWindowFocus:${hasWindowFocus()} isShown:${isShown}  visibility:${visibility} isShowHalf:${isShowHalf}")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            viewTreeObserver.removeOnWindowFocusChangeListener(focusChangeListener)
            viewTreeObserver.addOnWindowFocusChangeListener(focusChangeListener)
        }



        viewTreeObserver.removeOnScrollChangedListener(onScrollChangeListener)
        viewTreeObserver.addOnScrollChangedListener(onScrollChangeListener)
    }


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

//        Log.d("alvin", "MyTextView ${flagText} onDetachedFromWindow hasWindowFocus:${hasWindowFocus()} isShown:${isShown}  visibility:${visibility} isShowHalf:${isShowHalf}")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            viewTreeObserver.removeOnWindowFocusChangeListener(focusChangeListener)
        }
        viewTreeObserver.removeOnScrollChangedListener(onScrollChangeListener)

        //收集数据
        if (focusChangeNum < 1) {
            collectViewData()
        }
    }

    private fun collectViewData() {
//        Log.d("alvin", "MyTextView ${flagText} collectViewData isShowHalf:${isShowHalf}")
    }


}