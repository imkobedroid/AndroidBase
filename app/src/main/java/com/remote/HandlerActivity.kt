package com.remote

import android.annotation.SuppressLint
import android.app.Activity
import android.os.*
import kotlinx.android.synthetic.main.activity_handler.*
import org.jetbrains.anko.toast

/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 2019-05-22
 */
@SuppressLint("Registered")
class HandlerActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)
        initView()
    }

    private fun initView() {
        /**
         * 主线程通信  handler属于主线程
         */
        mainThread.setOnClickListener {

            val handler = @SuppressLint("HandlerLeak")
            object : Handler() {
                override fun handleMessage(msg: Message?) {
                    super.handleMessage(msg)
                    toast("主线程接受到消息----${msg?.what}")
                }
            }
            Thread(Runnable {
                SystemClock.sleep(3000)
                handler.sendEmptyMessage(0)
            }).start()
        }


        /**
         * 子线程通信 handler属于子线程
         */
        childThread.setOnClickListener {
            var mHandlerThread: Handler? = null
            Thread(Runnable {
                // SystemClock.sleep(5000)
                Looper.prepare()
                mHandlerThread = @SuppressLint("HandlerLeak")
                object : Handler() {
                    override fun handleMessage(msg: Message?) {
                        super.handleMessage(msg)
                        msg?.what?.let {
                            //(view as Button).text="子线程接受到消息----$it"
                            toast("子线程接受到消息----$it")
                        }
                    }
                }
                Looper.loop()
            }).start()
            SystemClock.sleep(3000)
            mHandlerThread!!.sendEmptyMessage(0)
        }

    }
}