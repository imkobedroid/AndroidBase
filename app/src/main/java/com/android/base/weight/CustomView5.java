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


    /**
     * 这里只需要ACTION_DOWN返回为true就行了，因为只有返回为true了才可以接受后续事件，但是后续事件返回为true或者false都没得影响
     * 返回false表示本次不消耗，但是父组件也不会走onTouchEvent，而是事件消失，发挥true表示消费此事件，所以这给子view设置了点击事件，让他的
     * down返回为true接受后续事件
     *
     * @param event
     * @return
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float oldX;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldX = event.getX();
                setProgressIndex(oldX);
//                getParent().requestDisallowInterceptTouchEvent(true);
//                return true;
                break;
            case MotionEvent.ACTION_MOVE:
                setProgressIndex(event.getX());
//                getParent().requestDisallowInterceptTouchEvent(true);
                return false;
//                break;
            default:
//                getParent().requestDisallowInterceptTouchEvent(false);
//                return false;
        }
//        boolean result = super.onTouchEvent(event);
//        Log.v(LOG, "super.onTouchEvent(event)" + result);
//        return result;

        return super.onTouchEvent(event);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                /**
                 *  在dispatchTouchEvent这方法中ACTION_DOWN的时候设置requestDisallowInterceptTouchEvent为true或者false都没得影响，
                 *  因为ACTION_DOWN事件只要父类不手动拦截都会传入他的子view，这里设置为true子view自己处理这个down，设置为false让父组件
                 *  可以进行拦截，但是父组件也不会拦截所以也是传递下来子view处理，所以这里设置false或者true并没有影响，关键是看onTouchEvent中对这个
                 *  down事件的返回值才是关键，因为onTouchEvent返回值直接影响后续事件需不需要这个子view处理
                 *
                 */

                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            default:
                getParent().requestDisallowInterceptTouchEvent(false);
        }
        return super.dispatchTouchEvent(event);
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
