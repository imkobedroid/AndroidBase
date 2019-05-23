package com.remote.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import com.aidlremoteapp.IMyAidlInterface
import com.remote.R
import com.remote.utils.Utils
import kotlinx.android.synthetic.main.activity_aidl.*
import org.jetbrains.anko.toast

/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 2019-05-22
 */
@SuppressLint("Registered")
class AidlActivity : Activity() {

    private var serviceConnection: ServiceConnection? = null
    var iCalculateAidlInterface: IMyAidlInterface? = null


    companion object {
        const val pkg = "com.aidlremoteapp"
        const val cls = "com.aidlremoteapp.IRemoteService"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aidl)
        bindService()
        initView()
    }

    private fun initView() {
        /**
         * 点击远程计算
         */
        progressInfo.setOnClickListener {
            //判断服务是否开启
            if (!Utils.isServiceRunning(this@AidlActivity, cls)) {
                toast("服务未开启")
                return@setOnClickListener
            }
            toast("3+2====${iCalculateAidlInterface?.add(2, 3)}")
        }
    }

    //绑定服务 拿到远程service
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
        intent.component = ComponentName(
            pkg,
            cls
        )
        bindService(intent, serviceConnection!!, Context.BIND_AUTO_CREATE)
    }


    override fun onDestroy() {
        super.onDestroy()
        serviceConnection?.let {
            unbindService(it)
        }
    }
}