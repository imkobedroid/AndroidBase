package com.aidlremoteapp

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 2019-05-19
 */
@SuppressLint("Registered")
class IRemoteService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return object : IMyAidlInterface.Stub() {
            override fun add(a: Int, b: Int): Int {
                return a + b
            }
        }
    }
}