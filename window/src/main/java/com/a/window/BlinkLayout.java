package com.a.window;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public  class BlinkLayout extends FrameLayout {
    private static final int MESSAGE_BLINK = 0x42;
    private static final int BLINK_DELAY = 500;

    private boolean mBlink;
    private boolean mBlinkState;
//    private final Handler mHandler;

    public BlinkLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
//        mHandler = new Handler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//                if (msg.what == MESSAGE_BLINK) {
//                    if (mBlink) {
//                        mBlinkState = !mBlinkState;
//                        makeBlink();
//                    }
//                    invalidate();
//                    return true;
//                }
//                return false;
//            }
//        });
    }

//    private void makeBlink() {
//        Message message = mHandler.obtainMessage(MESSAGE_BLINK);
//        mHandler.sendMessageDelayed(message, BLINK_DELAY);
//    }

//    @Override
//    protected void onAttachedToWindow() {
//        super.onAttachedToWindow();
//
//        mBlink = true;
//        mBlinkState = true;
//
//        makeBlink();
//    }
//
//    @Override
//    protected void onDetachedFromWindow() {
//        super.onDetachedFromWindow();
//
//        mBlink = false;
//        mBlinkState = true;
//
//        mHandler.removeMessages(MESSAGE_BLINK);
//    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
//        if (mBlinkState) {
            super.dispatchDraw(canvas);
//        }
    }
}
