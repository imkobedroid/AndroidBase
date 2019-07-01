package com.remote.activity.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.remote.R;

/**
 * Paint.setStyle(Style style) 设置绘制模式
 * Paint.setColor(int color) 设置颜色
 * Paint.setStrokeWidth(float width) 设置线条宽度
 * Paint.setTextSize(float textSize) 设置文字大小
 * Paint.setAntiAlias(boolean aa) 设置抗锯齿开关
 */


/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 2019-06-28
 */

public class CustomView1 extends View {

    Context context;

    final static String LOG = "测量";


    private Paint paint;

    public CustomView1(Context context) {
        super(context);
        this.context = context;
    }

    public CustomView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }


    public CustomView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView1(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }


    private void init() {
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.colorAccent));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        /**
         * ①UNSPECIFIED：表示默认值，父控件没有给子view任何限制。------二进制表示：00
         *
         * ②EXACTLY：表示父控件给子view一个具体的值，子view要设置成这些值的大小。------二进制表示：01
         * 具体的值(如width=200dp)和matchparent/fillparent，对应模式中的MeasureSpec.EXACTLY
         *
         * ③AT_MOST：表示父控件个子view一个最大的特定值，而子view不能超过这个值的大小。------二进制表示：10
         * 包裹内容(width=wrapcontent)则对应模式中的MeasureSpec.AT_MOST
         */

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0;
        int height = 0;

        switch (widthMode) {
            case MeasureSpec.UNSPECIFIED:
                Log.d(LOG, "WIDTH_UNSPECIFIED");
                width = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                Log.d(LOG, "WIDTH_AT_MOST");
                width = widthSize;
                break;
            case MeasureSpec.EXACTLY:
                Log.d(LOG, "WIDTH_EXACTLY");
                width = widthSize;
                break;
            default:
        }

        switch (heightMode) {
            case MeasureSpec.UNSPECIFIED:
                Log.d(LOG, "HEIGHT_UNSPECIFIED");
                height = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                Log.d(LOG, "HEIGHT_AT_MOST");
                height = heightSize;
                break;
            case MeasureSpec.EXACTLY:
                Log.d(LOG, "HEIGHT_EXACTLY");
                height = heightSize;
                break;
            default:
        }
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        //半径
        /**
         * getWidth() 当前这个自定义view的宽
         * getPaddingLeft() 当前这个自定义view的padding
         */
        int radius1 = getWidth() / 2;
        int radius2 = getHeight() / 2;
        int radius = Math.min(radius1, radius2);
        //圆心
        int x = radius + getPaddingLeft();
        int y = radius + getTop();
        //这里写死  相对的位置是在组件内的位置
//        canvas.drawCircle(100, 100, 100, paint);
        canvas.drawCircle(radius, radius, radius, paint);
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


}
