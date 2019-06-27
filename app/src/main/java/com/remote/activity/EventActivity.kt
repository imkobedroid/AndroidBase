package com.remote.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.MotionEvent.*
import com.remote.R
import kotlinx.android.synthetic.main.activity_event.*

/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 2019-06-26
 */
@SuppressLint("Registered")
class EventActivity : Activity() {

    companion object {
        const val LOG_ID = "点击事件"
        const val LOG_ID_TOUCH = "TOUCH点击事件"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        initView()
    }

    private fun initView() {
//        event_1.setOnClickListener { }
//        viewC.setOnClickListener {
//            toast("我是C消费了此事件")
//        }
//        viewD.setOnClickListener {
//            toast("我是D消费了此事件")
//        }


//        viewGroupB.setOnClickListener {
//            toast("我是B消费了此事件")
//        }


        viewC.setOnTouchListener { v, event ->
            Log.d(LOG_ID_TOUCH, "我是TouchListener")
            when (event.action) {
                0 -> Log.d(LOG_ID_TOUCH, "ACTION_DOWN")
                1 -> Log.d(LOG_ID_TOUCH, "ACTION_UP")
                2 -> Log.d(LOG_ID_TOUCH, "ACTION_MOVE")
                3 -> Log.d(LOG_ID_TOUCH, "ACTION_CANCEL")
            }
            false
        }

//        viewC.setOnClickListener {
//            Log.d(LOG_ID_TOUCH, "我是onclick")
//        }

        viewGroupB.setOnTouchListener { v, event ->
            Log.d(LOG_ID_TOUCH, "我是viewGroupB的TouchListener")
            when (event.action) {
                0 -> Log.d(LOG_ID_TOUCH, "我是viewGroupB的ACTION_DOWN")
                1 -> Log.d(LOG_ID_TOUCH, "我是viewGroupB的ACTION_UP")
                2 -> Log.d(LOG_ID_TOUCH, "我是viewGroupB的ACTION_MOVE")
                3 -> Log.d(LOG_ID_TOUCH, "我是viewGroupB的ACTION_CANCEL")
            }
            true
        }
    }


    /**
     * 点击事件第一步走到这里
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            ACTION_DOWN -> Log.v(LOG_ID, "ACTION_DOWN")
            ACTION_UP -> Log.v(LOG_ID, "ACTION_UP")
            ACTION_MOVE -> Log.v(LOG_ID, "ACTION_MOVE")
            ACTION_CANCEL -> Log.v(LOG_ID, "ACTION_CANCEL")
        }
        return super.dispatchTouchEvent(ev)
    }
}