package com.android.base.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.*
import android.util.Log
import com.android.base.R
import com.android.data.HandlerData
import kotlinx.android.synthetic.main.activity_handler.*
import org.jetbrains.anko.toast

/**
 * @author Dsh imkobedroid@gmail.com
 * @date 2019-05-22
 */
@SuppressLint("Registered")
class HandlerActivity : Activity() {


    companion object {
        fun getMessage(): Message {
            val msg = Message()
            msg.what = 1
            msg.arg1 = 2
            msg.arg2 = 3
            val bundle = Bundle()
            bundle.putString("key1", "4")
            msg.data = bundle
            msg.obj = HandlerData("5")
            return msg
        }
    }


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
            object : ChildHandler() {
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
            var mHandlerThread: ChildHandler? = null
            Thread(Runnable {
                // SystemClock.sleep(5000)
                Looper.prepare()
                mHandlerThread = @SuppressLint("HandlerLeak")
                object : ChildHandler() {
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


        /**
         * 一般的消息发送
         */
        messageSend.setOnClickListener {
            val handler = @SuppressLint("HandlerLeak")
            object : ChildHandler() {
                override fun handleMessage(msg: Message?) {
                    super.handleMessage(msg)
                    val par1 = msg?.what
                    val par2 = msg?.arg1
                    val par3 = msg?.arg2
                    val par4 = msg?.data?.getString("key1")
                    val par5 = (msg?.obj as HandlerData).info
                    Log.e("消息结果:", "$par1  $par2  $par3  $par4  $par5")
                }
            }
            Thread(Runnable {
                handler.sendMessage(getMessage())
                SystemClock.sleep(2000)
                handler.postDelayed({ handler.sendMessage(getMessage()) }, 0)
                SystemClock.sleep(2000)
                handler.post { toast("我是子线程中的post") }
            }).start()
        }
    }


    /**
     * 防止内存泄漏
     */
    open class ChildHandler : Handler() {
        fun getMessageInfo(handler: Handler): Message {
            return handler.obtainMessage()
        }
    }
}