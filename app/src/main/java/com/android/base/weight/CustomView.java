package com.android.base.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import com.android.base.R;
/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 2019-06-28
 */
public class CustomView extends View {
    private int mFirstColor;
    private int mSecondColor;
    private int mCircleWidth;

    private Paint mPaint, paint;

    private int j = 0;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CustomView_firstColor:
                    mFirstColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomView_secondColor:
                    mSecondColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomView_circleWidth:
                    mCircleWidth = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                default:
            }
        }
        a.recycle();

        mPaint = new Paint();
        paint = new Paint();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int centre = getWidth() / 2;
        int centre2 = getHeight() / 2;
        int radius = centre - mCircleWidth / 2;//半径
        mPaint.setStrokeWidth(mCircleWidth);//设置线宽
        mPaint.setAntiAlias(true);//消除锯齿
        mPaint.setStyle(Paint.Style.STROKE);//设置为空心
        mPaint.setStrokeCap(Paint.Cap.ROUND);//设置为线段形状为圆头
        //设定一个圆弧的边界矩形
        RectF oval = new RectF(centre - radius, centre2 - radius, centre + radius, centre2 + radius);

        mPaint.setColor(mFirstColor);
        //注意这个设置的线宽是以半径长度为中线，两边扩展
        //       canvas.drawCircle(centre, centre2, radius, mPaint);
        for (int i = 0; i < 12; i++) {
            canvas.drawArc(oval, (270 + 30 * i) % 360, 10, false, mPaint);
        }
//        canvas.drawArc(oval, 270, 10, false, mPaint);
//        canvas.drawArc(oval, 300, 10, false, mPaint);
        if (j < 0) j = 0;
        j = j % 13;
        mPaint.setColor(mSecondColor);
        for (int k = 0; k < j; k++) {
            canvas.drawArc(oval, (270 + 30 * k) % 360, 10, false, mPaint);
        }

        paint.setColor(Color.LTGRAY);
        paint.setStrokeWidth(10);//设置线宽
        paint.setStrokeCap(Paint.Cap.ROUND);//设置为线段形状为圆头

        canvas.drawLine(centre - 100, centre2 - 50, centre - 100, centre2 + 50, paint);
        canvas.drawLine(centre - 100, centre2 - 50, centre - 50, centre2 - 50, paint);
        canvas.drawLine(centre - 50, centre2 - 50, centre + 20, centre2 - 100, paint);

        canvas.drawLine(centre - 100, centre2 + 50, centre - 50, centre2 + 50, paint);
        canvas.drawLine(centre - 50, centre2 + 50, centre + 20, centre2 + 100, paint);

        canvas.drawLine(centre + 20, centre2 - 100, centre + 20, centre2 + 100, paint);

        canvas.drawLine(centre + 40, centre2 - 50, centre + 100, centre2 - 100, paint);
        canvas.drawLine(centre + 40, centre2, centre + 100, centre2, paint);
        canvas.drawLine(centre + 40, centre2 + 50, centre + 100, centre2 + 100, paint);
    }

    private int xDown, xUp;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
                xUp = (int) event.getY();
                if (xUp > xDown) {
                    j++;
                } else {
                    j--;
                }
                postInvalidate();//对于这个还有一点不理解，为什么不直接调用draw呢，而且也没有开启线程吧，为什么不能调用Invalidate()
                break;
            default:
        }
        return true;
    }
}