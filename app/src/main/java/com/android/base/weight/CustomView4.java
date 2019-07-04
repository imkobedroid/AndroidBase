package com.android.base.weight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.*;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.RequiresApi;

/**
 * 自定义圆形进度条
 *
 * @author Dsh  imkobedroid@gmail.com
 * @date 2019-07-02
 */

public class CustomView4 extends View {
    private Paint paint1;
    private Paint paint2;
    private Paint paint3;
    private int sweepAngle = 0;

    public CustomView4(Context context) {
        super(context);
    }

    public CustomView4(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public CustomView4(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView4(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private void init() {
        paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setColor(Color.BLACK);
        paint1.setStrokeWidth(5);
        paint1.setStyle(Paint.Style.STROKE);

        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setColor(Color.RED);
        paint2.setStrokeWidth(10);
        paint2.setStyle(Paint.Style.STROKE);

        paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint3.setColor(Color.RED);
        paint3.setTextSize(50);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width;
        int height;
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int min = Math.min(widthSize, heightSize);
        height = min;
        width = min;
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();


        int circleX = (width - getPaddingLeft() - getPaddingRight()) / 2;
        int circleY = (height - getPaddingTop() - getPaddingBottom()) / 2;

        int minCircle = Math.min(circleX, circleY);

        int left = (width - minCircle * 2) / 2;
        int top = (height - minCircle * 2) / 2;

        canvas.drawCircle(minCircle + left, minCircle + top, minCircle, paint1);


        @SuppressLint("DrawAllocation") RectF rectF = new RectF(left, top, minCircle * 2 + left, minCircle * 2 + top);
        canvas.drawArc(rectF, -90, sweepAngle, false, paint2);


        String progress = sweepAngle * 100 / 360 + "%";
        @SuppressLint("DrawAllocation") Rect rect = new Rect();
        paint3.getTextBounds(progress, 0, progress.length(), rect);
        int w = rect.width();
        int h = rect.height();
        int textX = minCircle + left - w / 2;
        canvas.drawText(progress, textX, minCircle + top, paint3);

        sweepAngle += 5;
        if (sweepAngle <= 360) {
            invalidate();
        }
    }
}
