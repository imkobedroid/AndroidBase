package com.android.base.weight;

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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                /**
                 *  在dispatchTouchEvent这方法中ACTION_DOWN的时候设置requestDisallowInterceptTouchEvent为true或者false都没得影响，
                 *  因为ACTION_DOWN事件只要父类不手动拦截都会传入他的子view，这里设置为true子view自己处理这个down，设置为false让父组件
                 *  可以进行拦截，但是父组件也不会拦截所以也是传递下来子view处理，所以这里设置false或者true并没有影响，关键是看onTouchEvent中对这个
                 *  down事件的返回值才是关键，因为onTouchEvent返回值直接影响后续事件需不需要这个子view处理
                 *
                 */
                getParent().requestDisallowInterceptTouchEvent(true);

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
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    /**
     * 是否已经滑到了最右边
     */
    private boolean isScrollToRight() {
        return getChildAt(getChildCount() - 1).getRight() == getScrollX() + getWidth();
    }

    /**
     * 是否已经滑到了最左边
     */
    private boolean isScrollToLeft() {
        return getScrollX() == 0;
    }
}
