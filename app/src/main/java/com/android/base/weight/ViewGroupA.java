package com.android.base.weight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import com.android.base.utils.ViewUtils;

import static com.android.base.activity.EventActivity.LOG_ID;

/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 2019-06-26
 */
public class ViewGroupA extends LinearLayout {
    public ViewGroupA(Context context) {
        super(context);
    }

    public ViewGroupA(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupA(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(LOG_ID, this.getClass().getSimpleName() + " onInterceptTouchEvent -> " + ViewUtils.actionToString(ev.getAction()));
        boolean result = super.onInterceptTouchEvent(ev);
        Log.d(LOG_ID, this.getClass().getSimpleName() + " onInterceptTouchEvent return super.onInterceptTouchEvent(ev)=" + result);
        return result;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(LOG_ID, this.getClass().getSimpleName() + " dispatchTouchEvent -> " + ViewUtils.actionToString(ev.getAction()));
        boolean result = super.dispatchTouchEvent(ev);
        Log.d(LOG_ID, this.getClass().getSimpleName() + " dispatchTouchEvent return super.dispatchTouchEvent(ev)= " + result);
        return result;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d(LOG_ID, this.getClass().getSimpleName() + " onTouchEvent -> " + ViewUtils.actionToString(ev.getAction()));
        boolean result = super.onTouchEvent(ev);
        Log.d(LOG_ID, this.getClass().getSimpleName() + " onTouchEvent return super.onTouchEvent(ev)=" + result);
        return result;
    }

}