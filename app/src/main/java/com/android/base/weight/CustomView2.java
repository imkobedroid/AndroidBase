package com.android.base.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.RequiresApi;

/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 2019-07-01
 */
public class CustomView2 extends View {


    private Paint paint;


    public CustomView2(Context context) {
        super(context);
    }

    public CustomView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    public CustomView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private void initView() {
        //抗锯齿
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        //画线条模式
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        //端点有圆头 (ROUND)、平头 (BUTT) 和方头 (SQUARE)
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画点的集合
        float[] points = {0, 0, 50, 50, 50, 100, 100, 50, 100, 100, 150, 50, 150, 100};
        /**
         * 参数1  画点的集合
         * 参数2  跳过前面几个点坐标
         * 参数3  画几个点（两个坐标为一个点  8表示画四个点）
         * 参数4  画笔
         */
        canvas.drawPoints(points, 2, 8, paint);


        //画圆
        //画空心圆
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(50, 200, 30, paint);
        //画实心圆
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(50, 300, 30, paint);

        //画椭圆
        paint.setStyle(Paint.Style.FILL);
        canvas.drawOval(10, 400, 100, 450, paint);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawOval(10, 500, 100, 550, paint);


        //画线条
        float[] points1 = {0, 0, 250, 50, 250, 100, 200, 50, 200, 100, 250, 50, 250, 100};
        /**
         * 参数1  画点的集合
         * 参数2  跳过前面几个点坐标
         * 参数3  画几个点（两个坐标为一个点  8表示2个线条）
         * 参数4  画笔
         */
        canvas.drawLines(points1, 2, 8, paint);


        //画圆角矩形
        /**
         * left, top, right, bottom 是四条边的坐标，rx 和 ry 是圆角的横向半径和纵向半径。
         */
        paint.setStrokeWidth(5);
        canvas.drawRoundRect(200, 200, 400, 300, 30, 30, paint);

        //画圆弧
        /**
         * left, top, right, bottom 描述的是这个弧形所在的椭圆；startAngle 是弧形的起始角度（x 轴的正向，即正右的方向，是 0 度的位置；顺时针为正角度，逆时针为负角度），sweepAngle 是弧形划过的角度；useCenter 表示是否连接到圆心，如果不连接到圆心，就是弧形，如果连接到圆心，就是扇形。
         */
        //实心圆弧
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(200, 400, 300, 500, -90, 90, true, paint);
        canvas.drawArc(200, 500, 300, 600, 0, 180, true, paint);
        canvas.drawArc(200, 650, 300, 750, -180, 180, true, paint);
        canvas.drawArc(200, 700, 300, 750, 0, 80, true, paint);

        paint.setStyle(Paint.Style.STROKE);
        // 绘制不封口的弧形
        canvas.drawArc(200, 800, 800, 1400, 180, 60, false, paint);
        canvas.drawArc(200, 1000, 800, 1200, 180, 60, false, paint);
        canvas.drawArc(200, 1100, 800, 1300, 180, 60, false, paint);


    }
}
