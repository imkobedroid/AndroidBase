package com.remote.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.MotionEvent.*
import com.remote.R
import kotlinx.android.synthetic.main.activity_event.*
import org.jetbrains.anko.toast

/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 2019-06-26
 */
@SuppressLint("Registered")
class EventActivity : Activity() {

    companion object {
        const val LOG_ID = "点击事件"
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


        viewGroupB.setOnClickListener {
            toast("我是B消费了此事件")
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