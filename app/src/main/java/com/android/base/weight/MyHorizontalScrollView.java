package com.android.base.weight;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 2019-07-04
 */
public class MyHorizontalScrollView extends HorizontalScrollView {
    public MyHorizontalScrollView(Context context) {
        super(context);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    /**
     * 使用getParent().requestDisallowInterceptTouchEvent(true)表示当前组件消耗事件，
     * 如果当前组件的super方法中有消耗事件的操作，这只需要在最后返回super.onTouchEvent(ev);
     * 如果super方法中没有消耗事件的操作，则使用getParent().requestDisallowInterceptTouchEvent(true)必须返回一个true表示当前消耗了这个事件，
     * 如果super方法中没有消耗事件的操作，则使用getParent().requestDisallowInterceptTouchEvent(false)必须返回一个false表示当前消耗了这个事件
     */
//    @SuppressLint("ClickableViewAccessibility")
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                getParent().requestDisallowInterceptTouchEvent(true);
//                return super.onTouchEvent(ev);
//            case MotionEvent.ACTION_MOVE:
//                if (isScrollToRight() || isScrollToLeft()) {
//                    getParent().requestDisallowInterceptTouchEvent(false);
//                    return false;
//                } else {
//                    return super.onTouchEvent(ev);
//                }
//            case MotionEvent.ACTION_UP:
//                getParent().requestDisallowInterceptTouchEvent(false);
//                return false;
//            default:
//                getParent().requestDisallowInterceptTouchEvent(true);
//                return super.onTouchEvent(ev);
//        }
//    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                if (isScrollToRight() || isScrollToLeft()) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                getParent().requestDisallowInterceptTouchEvent(false);
            default:
        }
        return super.onTouchEvent(ev);
    }


    /**
     * 是否已经滑到了最右边
     *
     * @return
     */
    private boolean isScrollToRight() {
        return getChildAt(getChildCount() - 1).getRight() == getScrollX() + getWidth();
    }

    /**
     * 是否已经滑到了最左边
     *
     * @return
     */
    private boolean isScrollToLeft() {
        return getScrollX() == 0;
    }
}
