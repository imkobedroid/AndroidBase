package com.remote.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import com.remote.R

/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 2019-06-28
 */
@SuppressLint("Registered")
class CustomActivity  :Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom)
    }

}