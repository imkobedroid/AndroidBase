package com.android.base.weight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 2019-07-04
 */
public class ViewE extends View {

    public final static String LOG = "测试事件";

    public ViewE(Context context) {
        super(context);
    }

    public ViewE(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewE(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ViewE(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                //通知父组件是否来处理本次事件，true表示父组件不拦截本组件自己处理，走下面的super.dispatchTouchEvent(event); false表示父组件拦截由父组件处理
//                getParent().requestDisallowInterceptTouchEvent(false);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                getParent().requestDisallowInterceptTouchEvent(false);
//                break;
//            default:
//        }

        boolean result = super.dispatchTouchEvent(event);
        Log.v(LOG, "ViewE  dispatchTouchEvent  " + result);
        return result;
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.v(LOG, "ViewE  ACTION_DOWN");
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.v(LOG, "ViewE  ACTION_MOVE");
                return true;
            case MotionEvent.ACTION_UP:
                Log.v(LOG, "ViewE  ACTION_UP");
                return true;
            default:
        }
        return super.onTouchEvent(event);
    }
}
