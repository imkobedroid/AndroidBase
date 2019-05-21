package com.remote

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatButton
import android.widget.Button
import android.widget.Toast
import android.content.Intent
import com.aidlremoteapp.IMyAidlInterface


class MainActivity : AppCompatActivity() {
    private var serviceConnection: ServiceConnection? = null
    var iCalculateAidlInterface: IMyAidlInterface? = null

    companion object {
        const val pkg = "com.aidlremoteapp"
        const val cls = "com.aidlremoteapp.IRemoteService"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindService()
        initView()

    }

    private fun initView() {

        /**
         * 主线程通信  handler属于主线程
         */
        findViewById<AppCompatButton>(R.id.mainThread).setOnClickListener {

            val handler = @SuppressLint("HandlerLeak")
            object : Handler() {
                override fun handleMessage(msg: Message?) {
                    super.handleMessage(msg)
                    msg?.what?.let { Toast.makeText(this@MainActivity, "主线程接受到消息----$it", Toast.LENGTH_SHORT).show() }
                }
            }
            Thread(Runnable {
                SystemClock.sleep(5000)
                handler.sendEmptyMessage(0)
            }).start()
        }


        /**
         * 子线程通信 handler属于子线程
         */
        findViewById<Button>(R.id.childThread).setOnClickListener {
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
                            Toast.makeText(this@MainActivity, "子线程接受到消息----$it", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                Looper.loop()
            }).start()
            SystemClock.sleep(5000)
            mHandlerThread!!.sendEmptyMessage(0)
        }

        /**
         * 点击远程计算
         */
        findViewById<Button>(R.id.progressInfo).setOnClickListener {
            //判断服务是否开启
            if (!utils.isServiceRunning(this@MainActivity, "com.aidlremoteapp.IRemoteService")) {
                Toast.makeText(this@MainActivity, "服务未开启", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(
                this@MainActivity,
                "3+2====${iCalculateAidlInterface?.add(2, 3)}",
                Toast.LENGTH_SHORT
            ).show()
        }


        findViewById<Button>(R.id.dataStructure).setOnClickListener {
            startActivity(Intent(this@MainActivity, DataStruActivity::class.java))
        }

    }

    //绑定服务
    private fun bindService() {
        serviceConnection = object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {
                iCalculateAidlInterface = null
            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                iCalculateAidlInterface = IMyAidlInterface.Stub.asInterface(service)
            }
        }

        val intent = Intent()
        intent.component = ComponentName(pkg, cls)
        bindService(intent, serviceConnection!!, Context.BIND_AUTO_CREATE)
    }


    override fun onDestroy() {
        super.onDestroy()
        serviceConnection?.let {
            unbindService(it)
        }
    }
}
