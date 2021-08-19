package com.a.window;

import android.view.Choreographer;

public class ChoreographerInvoke {

    private static final Object FRAME_CALLBACK_TOKEN = new Object() {
        public String toString() { return "FRAME_CALLBACK_TOKEN"; }
    };
    public static void  postFrameCallback(Choreographer.FrameCallback callback){
        try{
            RefInvoke.invokeInstanceMethod(
                    Choreographer.getInstance(),
                    "postCallbackDelayedInternal",
                    new Class[]{Integer.class,Object.class,Object.class,Long.class },
                    new Object[]{new Integer(2),callback,FRAME_CALLBACK_TOKEN,new Long(0)}

            );
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
