package com.a.window;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class TriangleView extends View {

    public TriangleView(Context context) {
        this(context, null);
    }

    public TriangleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TriangleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public boolean isInEditMode() {
        return true;
    }



    public void setColor(int color) {

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       int width = getWidth();
       int height=getHeight();
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#292929"));
        paint.setAntiAlias(true); //抗锯齿
        paint.setStyle(Paint.Style.FILL);//实线
        //创建路径
        Path path = new Path();
        path.moveTo(0,height);
        path.lineTo(width,height);
        path.lineTo(width/2,0);
        path.close();//闭合路径
        //画在画布上
        canvas.drawPath(path,paint);
    }
}
