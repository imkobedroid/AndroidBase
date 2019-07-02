package com.remote.activity.weight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 自定义能拖动的进度条
 *
 * @author Dsh  imkobedroid@gmail.com
 * @date 2019-07-02
 */
public class CustomView5 extends View {
    private Paint paint1;
    private Paint paint2;
    private Paint paint3;

    private int distanceX = 0;
    private int width = 0;
    private float progress = 0;

    /**
     * 默认半径
     */
    private int radius = 30;

    public CustomView5(Context context) {
        super(context);
    }

    public CustomView5(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    public CustomView5(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView5(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private void initView() {
        paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setColor(Color.GRAY);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(20);
        paint1.setStrokeCap(Paint.Cap.ROUND);

        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setColor(Color.RED);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(20);
        paint2.setStrokeCap(Paint.Cap.ROUND);


        paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint3.setStyle(Paint.Style.FILL);
        paint3.setColor(Color.RED);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        width = getWidth();

        //居中
        int distanceY = (getPaddingTop() + getPaddingBottom()) / 2;
        distanceX = (getPaddingLeft() + getPaddingRight()) / 2;

        canvas.drawLine(distanceX, distanceY, width - distanceX, distanceY, paint1);
        if (progress == 0) {
            canvas.drawCircle(distanceX, distanceY, radius, paint3);
        } else {
            canvas.drawLine(distanceX, distanceY, progress, distanceY, paint2);
            canvas.drawCircle(progress, distanceY, radius, paint3);
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float oldX;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldX = event.getX();
                if (oldX > progress - radius && oldX < progress + radius) {
                    setProgressIndex(oldX);
                    return true;
                }
            case MotionEvent.ACTION_MOVE:

                //点击不可以拖动进度条就用这个代码
//                oldX = event.getX();
//                if (oldX > progress - radius && oldX < progress + radius) {
//                    setProgressIndex(oldX);
//                    return true;
//                }

                //点击也可以拖动进度条用这个代码
                setProgressIndex(event.getX());
                return true;
            case MotionEvent.ACTION_UP:
                invalidate();
                return true;
            default:
        }
        return super.onTouchEvent(event);
    }


    private void setProgressIndex(float x) {
        if (x <= distanceX) {
            progress = distanceX;
        } else if (x >= width - distanceX) {
            progress = width - distanceX;
        } else {
            progress = x;
        }
        invalidate();
    }

}
