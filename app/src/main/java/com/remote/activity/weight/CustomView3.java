package com.remote.activity.weight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.*;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 2019-07-01
 */
public class CustomView3 extends View {

    private String[] strings = {"java", "python", "kotlin", "dart"};

    private final static int PADDING = 100;

    //画线条的笔
    private Paint paint1;
    //画文字的笔
    private Paint paint2;
    //画柱状图的笔
    private Paint paint3;

    //画圆环
    private Paint paint4;

    private Paint paint5;

    public CustomView3(Context context) {
        super(context);
    }

    public CustomView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public CustomView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView3(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setColor(Color.WHITE);
        paint1.setStrokeWidth(5);

        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setColor(Color.WHITE);
        paint2.setTextSize(36);

        paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint3.setColor(Color.GREEN);

        paint4 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint4.setStrokeWidth(40);
        paint4.setStyle(Paint.Style.STROKE);
        paint4.setColor(Color.WHITE);

        paint5 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint5.setStrokeWidth(40);
        paint5.setStyle(Paint.Style.STROKE);
        paint5.setStrokeCap(Paint.Cap.ROUND);
        paint5.setColor(Color.GREEN);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //屏幕的一半高
        int height = getHeight() / 2;
        int width = getWidth();
        canvas.drawLine(PADDING, PADDING, PADDING, height, paint1);
        canvas.drawLine(PADDING, height, width - PADDING, height, paint1);


        int position = (width - PADDING * 2) / 4 - 20;
        canvas.drawText(strings[0], PADDING + position, height + 40, paint2);
        canvas.drawText(strings[1], PADDING + position * 2, height + 40, paint2);
        canvas.drawText(strings[2], PADDING + position * 3, height + 40, paint2);
        canvas.drawText(strings[3], PADDING + position * 4, height + 40, paint2);


        canvas.drawRect(PADDING + position, height - 300, PADDING + position + 80, height, paint3);
        canvas.drawRect(PADDING + position * 2, height - 500, PADDING + position * 2 + 80, height, paint3);
        canvas.drawRect(PADDING + position * 3, height - 700, PADDING + position * 3 + 80, height, paint3);
        canvas.drawRect(PADDING + position * 4, height - 100, PADDING + position * 4 + 80, height, paint3);


        int index = getHeight() / 4 + height;
        int indexWidth = getWidth() / 2;
        canvas.drawCircle(indexWidth, index, 300, paint4);

        @SuppressLint("DrawAllocation") RectF rect1 = new RectF(indexWidth - 300, index - 300, indexWidth + 300, index + 300);
        canvas.drawArc(rect1, -90, 180, false, paint5);

        paint5.setColor(Color.YELLOW);
        @SuppressLint("DrawAllocation") RectF rect2 = new RectF(indexWidth - 300, index - 300, indexWidth + 300, index + 300);
        canvas.drawArc(rect2, -90, 90, false, paint5);

        //计算文字所在矩形，可以得到宽高
        @SuppressLint("DrawAllocation") Rect rect = new Rect();
        paint2.getTextBounds("Android", 0, "Android".length(), rect);
        int w = rect.width();
        //文字居中
        canvas.drawText("Android", indexWidth - w / 2, index, paint2);


    }
}
