package com.android.base.weight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import static com.android.base.weight.ViewE.LOG;

/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 2019-06-26
 */
public class ViewGroupE extends LinearLayout {
    public ViewGroupE(Context context) {
        super(context);
    }

    public ViewGroupE(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupE(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.v(LOG, "ViewGroupE  ACTION_DOWN");
                return false;
            case MotionEvent.ACTION_MOVE:
                Log.v(LOG, "ViewGroupE  ACTION_MOVE");
                return false;
            case MotionEvent.ACTION_UP:
                Log.v(LOG, "ViewGroupE  ACTION_UP");
                return false;
            default:
        }
        return super.onInterceptTouchEvent(event);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
        Log.v(LOG, "ViewGroupE  dispatchTouchEvent  " + result);
        return result;
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.v(LOG, "ViewGroupE-onTouchEvent  ACTION_DOWN");
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.v(LOG, "ViewGroupE-onTouchEvent  ACTION_MOVE");
                return true;
            case MotionEvent.ACTION_UP:
                Log.v(LOG, "ViewGroupE-onTouchEvent  ACTION_UP");
                return true;
            default:
        }
        return super.onTouchEvent(event);
    }
}