package com.android.base.weight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.RequiresApi;

/**
 * 自定义能拖动的进度条
 *
 * @author Dsh  imkobedroid@gmail.com
 * @date 2019-07-02
 */
public class CustomView5 extends View {


    private final String LOG = "冲突事件";
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
        int distanceY = getHeight() / 2;
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
                setProgressIndex(oldX);
                getParent().requestDisallowInterceptTouchEvent(true);
                return true;
            case MotionEvent.ACTION_MOVE:
                setProgressIndex(event.getX());
                getParent().requestDisallowInterceptTouchEvent(true);
                return true;
            default:
                getParent().requestDisallowInterceptTouchEvent(false);
                return false;
        }
//        boolean result = super.onTouchEvent(event);
//        Log.v(LOG, "super.onTouchEvent(event)" + result);
//        return result;
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


    /**
     * 判断当前点击事件是不是在这个控件上
     */
    public boolean calcViewScreenLocation(View view, MotionEvent motionEvent) {
        int[] location = new int[2];
        // 获取控件在屏幕中的位置，返回的数组分别为控件左顶点的 x、y 的值
        view.getLocationOnScreen(location);
        RectF rectF = new RectF(location[0], location[1], location[0] + view.getWidth(),
                location[1] + view.getHeight());
        return rectF.contains(motionEvent.getRawX(), motionEvent.getRawY());
    }
}
