package com.remote.activity.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import static com.remote.activity.EventActivity.LOG_ID;

/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 2019-06-26
 */

public class ViewGroupB extends LinearLayout {
    public ViewGroupB(Context context) {
        super(context);
    }

    public ViewGroupB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(LOG_ID, this.getClass().getSimpleName() + " onInterceptTouchEvent -> " + ViewUtils.actionToString(ev.getAction()));
        boolean result = super.onInterceptTouchEvent(ev);
        Log.d(LOG_ID, this.getClass().getSimpleName() + " onInterceptTouchEvent return super.onInterceptTouchEvent(ev)=" + result);
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(LOG_ID, this.getClass().getSimpleName() + " dispatchTouchEvent -> " + ViewUtils.actionToString(ev.getAction()));
        boolean result = super.dispatchTouchEvent(ev);
        Log.d(LOG_ID, this.getClass().getSimpleName() + " dispatchTouchEvent return super.dispatchTouchEvent(ev)= " + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d(LOG_ID, this.getClass().getSimpleName() + " onTouchEvent -> " + ViewUtils.actionToString(ev.getAction()));
        boolean result = super.onTouchEvent(ev);
        Log.d(LOG_ID, this.getClass().getSimpleName() + " onTouchEvent return super.onTouchEvent(ev)=" + result);
        return result;
    }
}
